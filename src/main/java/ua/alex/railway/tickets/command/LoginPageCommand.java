package ua.alex.railway.tickets.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginPageCommand  implements Command {

    @Override
    public String execute(HttpServletRequest request) {

            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("role");

            if (!"ROLE_GUEST".equals(role)) {
                return "redirect:/index.jsp";
            } else  {

                return request.getContextPath() + "/login.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
            }
    }
}
