package ua.alex.railway.tickets.command;

import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserListCommand implements Command{

    private final UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");
        System.out.println("User Name: " + name);
        System.out.println("Role: " + role);
        List<User> allUsers = userService.getAllUsers();
        System.out.println("All Users: ");
        for (User u : allUsers) System.out.println(u.getEmail());

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println("Context path: " + request.getContextPath());
            session.setAttribute("userName", name);
            session.setAttribute("role", role);
            request.setAttribute("allUsers", allUsers);
            return request.getContextPath() + "/user/usersPage.jsp";
        }


    }

}
