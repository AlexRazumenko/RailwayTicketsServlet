package ua.alex.railway.tickets.command.ticket;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.StationService;
import ua.alex.railway.tickets.service.TicketService;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketsByTrainAndDepartDateAndOccupiedCommand implements Command {

    private TicketService ticketService;
    private StationService stationService;
    private TrainService trainService;

    public TicketsByTrainAndDepartDateAndOccupiedCommand(
            TicketService ticketService, StationService stationService, TrainService trainService) {
        this.ticketService = ticketService;
        this.stationService = stationService;
        this.trainService = trainService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        User user = (User) session.getAttribute("currentUser");
        long trainId = Long.parseLong(request.getParameter("trainId"));
        LocalDate departDate = Date.valueOf(request.getParameter("departDate")).toLocalDate();
        Train train = trainService.findTrainById(trainId);

        List<TicketDTO> tickets = ticketService.findByTrainAndDepartDateAndOccupied(train, departDate, false);

        if (tickets.isEmpty()) {
            tickets = ticketService.fillTrainWithTickets(trainService.findTrainById(trainId), departDate,  user);
        }

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
request.setAttribute("train", trainService.findTrainById(trainId));
            request.setAttribute("tickets", tickets);
            return request.getContextPath() + "/train.jsp";
        }
    }

//    private List<TicketDTO> fillTrainWithTickets (Train train, LocalDate date, User user) {
//
//        List<Ticket> ticketList = new LinkedList<>();
//        Ticket ticket = new Ticket(train, date, 0, false, null);
//
//        for (int i = 1; i < 21; i++) {
//           ticket.setPlace(i);
//           ticketService.createTicket(ticket);
//           ticketList.add(ticket);
//        }
//
//        return ticketList.stream().map(ticket1 ->
//        new TicketDTO (ticket.getId(), ticket.getDepartDate(), ticket.getPlace(), ticket.isOccupied(),
//                train.getDepartTime(), train.getArriveTime(), train.getNumber(), train.getPrice(),
//                train.getArriveStation().getName(), train.getArriveStation().getName(),
//                user.getEmail(), user.getFirstName(), user.getLastName())).collect(Collectors.toList());//
//    }
}
