package ua.alex.railway.tickets.command.train;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.service.StationService;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TrainsByDepStationCommand implements Command {

    private TrainService trainService;
    private StationService stationService;

    public TrainsByDepStationCommand(TrainService trainService, StationService stationService) {
        this.trainService = trainService;
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        long departStationId = Long.parseLong(request.getParameter("departStationId"));
        List<Train> trains = trainService.findTrainsByDepartureStationId(departStationId);
        List<Station> allStations = stationService.getAllStations();

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
//            session.setAttribute("role", role);
//            request.setAttribute("mainMessage", "Trains management page");
            request.setAttribute("trains", trains);
            session.setAttribute("allStations", allStations);
            return request.getContextPath() + "/timetable.jsp";
        }
    }
}
