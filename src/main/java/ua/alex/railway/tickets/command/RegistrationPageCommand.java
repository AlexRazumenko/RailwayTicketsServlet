package ua.alex.railway.tickets.command;

import ua.alex.railway.tickets.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("userName");
        String role = (String) session.getAttribute("role");
        System.out.println("User Name: " + name);
        System.out.println("Role: " + role);


        if ("ROLE_GUEST".equals(role)) {
            request.setAttribute("newUser", new User());
            return request.getContextPath() + "/user/register.jsp";
//            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println("Context path: " + request.getContextPath());
            request.setAttribute("userName", name);
            request.setAttribute("role", role);
            session.setAttribute("mainMessage", "You are already logged in.");
            return request.getContextPath() + "/index.jpg";
//            return "redirect:/WEB-INF/user/userPage.jsp";
        }

//        return null;
    }
}
