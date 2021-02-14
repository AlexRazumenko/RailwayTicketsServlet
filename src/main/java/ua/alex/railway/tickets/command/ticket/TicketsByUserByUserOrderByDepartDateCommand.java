package ua.alex.railway.tickets.command.ticket;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.TicketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TicketsByUserByUserOrderByDepartDateCommand implements Command {

    private TicketService ticketService;

    public TicketsByUserByUserOrderByDepartDateCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        User user = (User) session.getAttribute("currentUser");

        List<TicketDTO> allTicketsByUser = ticketService.findTicketsByUserOrderByDepartDate(user);

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)

            request.setAttribute("myTickets", allTicketsByUser);

            return request.getContextPath() + "/admin/ticketns.jsp";
        }
    }
}
