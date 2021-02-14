package ua.alex.railway.tickets.command;

import ua.alex.railway.tickets.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserPageCommand implements Command {

    private final UserService userService;

    public UserPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");
        System.out.println("User Name: " + name);
        System.out.println("Role: " + role);

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println("Context path: " + request.getContextPath());
            request.setAttribute("userName", name);
            request.setAttribute("role", role);
            return request.getContextPath() + "/user/userPage.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
        }

//        return null;
    }

}
