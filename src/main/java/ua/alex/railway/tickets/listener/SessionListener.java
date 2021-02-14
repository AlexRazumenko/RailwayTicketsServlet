package ua.alex.railway.tickets.listener;

import ua.alex.railway.tickets.entity.RoleType;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashSet;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionBindingListener, HttpSessionActivationListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute("userName", "Guest");
        session.setAttribute("role", RoleType.ROLE_GUEST.name());
        session.setAttribute("mainMessage", "Welcome, Guest! Please log in to make your order.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext ctx = session.getServletContext();
        String userName = (String) session.getAttribute("userName");

        HashSet<String> loggedUsers = (HashSet<String>) ctx.getAttribute("loggedUsers");
        loggedUsers.remove(userName);

        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("valueBound: " + event.getName() + " : " + event.getValue());
        System.out.println("  session: " + event.getSession().getId());
//        this.printStackTrace();
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("valueUnbound: " + event.getName() + " : " + event.getValue());
        System.out.println("  session: " + event.getSession().getId());
//        this.printStackTrace();
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("attributeAdded: " + event.getName() + " : " + event.getValue());
        System.out.println("  session: " + event.getSession().getId());
//        this.printStackTrace();
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("attributeRemoved: " + event.getName() + " : " + event.getValue());
        System.out.println("  session: " + event.getSession().getId());
//        this.printStackTrace();
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("attributeReplaced: " + event.getName() + " : " + event.getValue());
        System.out.println("  session: " + event.getSession().getId());
//        this.printStackTrace();
    }

    public void sessionDidActivate(HttpSessionEvent event) {
        System.out.println("sessionDidActivate: " + event.getSession().getId());
//        this.printStackTrace();
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent event) {
        System.out.println("sessionWillPassivate: " + event.getSession().getId());
//        this.printStackTrace();
    }
}
