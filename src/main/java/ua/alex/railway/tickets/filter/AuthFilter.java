package ua.alex.railway.tickets.filter;

import ua.alex.railway.tickets.entity.RoleType;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", servletNames = {"MainServlet"})
public class AuthFilter implements Filter {

    private UserService userService = new UserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        ServletContext ctx = session.getServletContext();
          String userName = (String) session.getAttribute("userName");
        System.out.println("User name in filter: " + userName);
          servletRequest.setAttribute("userName", userName);
          servletRequest.setAttribute("role", session.getAttribute("role"));
        System.out.println("In filter.");

//        Enumeration<String> e = session.getAttributeNames();
//        while (e.hasMoreElements()) {
//            System.out.println(e.nextElement());
//        }
//        System.out.println();

        User user = "Guest".equals(userName)
                ? new User("Guest", RoleType.ROLE_GUEST) : userService.findUserByEmail(userName);
        String roleType = (String) session.getAttribute("role");

        switch (roleType) {
            case "ROLE_USER":
                if (req.getRequestURI().contains("admin")) {
//                    servletRequest.setAttribute("message", "Access denied");
//                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/error.jsp");
//                    dispatcher.forward(servletRequest, servletResponse);
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    servletRequest.setAttribute("message", "Welcome " + user.getFirstName());
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                break;
            case "ROLE_ADMIN":
                filterChain.doFilter(servletRequest, servletResponse);
                break;
            default:
                servletRequest.setAttribute("message", "Please log in to continue");
//                servletRequest.setAttribute("message", "Please log in to continue");

                filterChain.doFilter(servletRequest, servletResponse);
//                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login.jsp");
//                dispatcher.forward(servletRequest, servletResponse);
                break;
        }


    }

    @Override
    public void destroy() {

    }
}
