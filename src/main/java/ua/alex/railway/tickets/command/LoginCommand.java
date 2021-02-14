package ua.alex.railway.tickets.command;

import ua.alex.railway.tickets.entity.RoleType;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;

public class LoginCommand implements Command{

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");

        Optional<User> loggedUserOpt = Optional.ofNullable(userService.findUserByEmail(login));
//        User loggedUser = null;
//        final String indexAddress = request.getContextPath() + "/index.jsp";
        final String loginAddress = request.getContextPath() + "/login.jsp";
        String message;

        if (loggedUserOpt.isPresent()) {
            User loggedUser = loggedUserOpt.get();
            if (password.equals(loggedUser.getPassword())) {
                HashSet<String> loggedUsers = (HashSet<String>) request.getServletContext().getAttribute("loggedUsers");
                if (!loggedUsers.contains(login)) loggedUsers.add(login);
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", loggedUser);
                request.setAttribute("mainMessage", "Welcome, " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
                session.setAttribute("userName", loggedUser.getFirstName() + " " + loggedUser.getLastName());
                request.setAttribute("roleMessage", "Role: " + loggedUser.getRole().name());
                return request.getContextPath() + "/index.jsp";
            }
            message = "Incorrect password";
        } else
            message = "Failed to login, check your credentials";
                    request.setAttribute("loginPageMessage", message);

        return request.getContextPath() + "/login.jsp";
    }
}
