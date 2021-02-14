package ua.alex.railway.tickets.command.ticket;

import ua.alex.railway.tickets.command.Command;
//import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.TicketService;
import ua.alex.railway.tickets.service.TrainService;
import ua.alex.railway.tickets.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class AddTicketCommand implements Command {
    private TicketService ticketService;
    private TrainService trainService;
    private UserService userService;

    public AddTicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("ROLE_GUEST".equals(role)) {

            String name = request.getParameter("name");

            String departDateStr = request.getParameter("date");
            System.out.println(departDateStr);


//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date startDate = sdf.parse(startDateStr); // you will get date here
//            LocalDate localDate = LocalDate


            Train train = trainService.findTrainById(Long.parseLong(request.getParameter("train_id")));
            User user  = userService.findUserById(Long.parseLong(request.getParameter("user_id")));
//            LocalDate departureDate = ((java.sql.Date) request.getParameter("departure_date")).toLocalDate();
//            int departStationId = Integer.parseInt(request.getParameter("departStationId"));
            int place = Integer.parseInt(request.getParameter("place"));
            boolean isOccupied = Boolean.parseBoolean(request.getParameter("occupied"));

            //public Ticket(Train train, LocalDate departDate, int place, boolean isOccupied, User user) {
            Ticket newTicket = new Ticket(train, LocalDate.now(), place, isOccupied, user);




            ticketService.createTicket(newTicket);

            session.setAttribute("mainMessage", "Congratulations! You've succesfully bought a ticket.");
//            request.setAttribute("allTickets", ticketService.findByUserOrderByDepartDate(user));

            request.setAttribute("currentTicket", newTicket);

//            return request.getContextPath() + "/index.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
//            return "redirect:/WEB-INF/admin/adminPage.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
            System.out.println("else");
        }
        return request.getContextPath() + "/user/ticket.jsp";
    }
}
