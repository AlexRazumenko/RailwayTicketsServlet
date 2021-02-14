package ua.alex.railway.tickets.command;

import ua.alex.railway.tickets.entity.RoleType;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegistrationCommand implements Command {

    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");
        System.out.println("\nRegistration: ");
        System.out.println("User Name: " + name);
        System.out.println("Role: " + role);

        if ("ROLE_GUEST".equals(role)) {
            System.out.println("Context path: " + request.getContextPath());
            session.setAttribute("userName", name);
            session.setAttribute("role", role);

            String email = (String) request.getParameter("email");
            String password = (String) request.getParameter("pass");
            String firstName = (String) request.getParameter("firstName");
            String lastName = (String) request.getParameter("lastName");
            System.out.println("Parameters: " + email + " " + password + " " + firstName + " " + lastName);


            User newUser = User.userBuilder()
                    .withEmail(email)
                    .withPassword(password)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withRole(RoleType.ROLE_USER)
                    .build();

            System.out.println(newUser);
//            (User) request.getAttribute("newUser")
            userService.createUser(newUser);

            session.setAttribute("mainMessage", "User successfully registered.\n Please log in for further actions");

//            return request.getContextPath() + "/index.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
//            return "redirect:/WEB-INF/admin/adminPage.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
            System.out.println("else");
        }
        return request.getContextPath() + "/index.jsp";
    }
}
