package ua.alex.railway.tickets.command.ticket;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.TicketService;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.stream.IntStream;

public class BookTicketCommand implements Command {

    private TicketService ticketService;
    private TrainService trainService;

    public BookTicketCommand(TicketService ticketService, TrainService trainService) {
        this.ticketService = ticketService;
        this.trainService = trainService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        Long trainId = Long.parseLong(request.getParameter("trainId"));
        String departDate = request.getParameter("departDate");
        int place = Integer.parseInt(request.getParameter("place"));
        Train train = trainService.findTrainById(trainId);
        User user = (User) session.getAttribute("currentUser");
        System.out.println("Current user: " + user);

        Ticket newTicket = new Ticket(train, LocalDate.parse(departDate), place, true, user);
        ticketService.createTicket(newTicket);

        if ("ROLE_GUEST".equals(role)) {

//            boolean isOccupied = Boolean.parseBoolean(request.getParameter("occupied"));

//            ticketService.createTicket(newTicket);

            session.setAttribute("mainMessage", "Congratulations! You've succesfully bought a ticket.");
//            request.setAttribute("allTickets", ticketService.findByUserOrderByDepartDate(user));

            request.setAttribute("myTickets", ticketService.findTicketsByUserOrderByDepartDate(user));

//            return request.getContextPath() + "/index.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
//            return "redirect:/WEB-INF/admin/adminPage.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
            System.out.println("else");
        }
        return request.getContextPath() + "/user/tickets.jsp";
    }
}
