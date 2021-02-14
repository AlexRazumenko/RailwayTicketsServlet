package ua.alex.railway.tickets.command.train;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.service.TicketService;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TrainFreeSeatsCommand implements Command {

    private TrainService trainService;
    private TicketService ticketService;

    public TrainFreeSeatsCommand(TrainService trainService, TicketService ticketService) {
        this.trainService = trainService;
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("In TrainCommand");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        System.out.println(user);
        String role = (String) session.getAttribute("role");
        long id = Long.parseLong(request.getParameter("trainId"));
        System.out.println(id);
        Train train = trainService.findTrainById(id);
        String departDate = request.getParameter("departDate");
        System.out.println(departDate);
//        List<TicketDTO> tickets = ticketService.findByTrainAndDepartDateAndOccupied
//                (train, Date.valueOf(departDate).toLocalDate(), false);
        List<Integer> freeSeatsNumbers = ticketService
                .findFreeSeatsNumbersByTrainAndDepartDate(id, Date.valueOf(departDate).toLocalDate());
        System.out.println(freeSeatsNumbers);
        System.out.println(freeSeatsNumbers.size());

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println(role);
            System.out.println(train);
//            session.setAttribute("role", role);
            request.setAttribute("train", train);
            request.setAttribute("departDate", departDate);
            request.setAttribute("freeSeats", freeSeatsNumbers);
            return request.getContextPath() + "/user/train.jsp";
        }
    }
}
