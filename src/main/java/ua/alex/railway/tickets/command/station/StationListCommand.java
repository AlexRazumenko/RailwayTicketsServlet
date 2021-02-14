package ua.alex.railway.tickets.command.station;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class StationListCommand implements Command {

    private StationService stationService;

    public StationListCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        List<Station> allStations = stationService.getAllStations();

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            session.setAttribute("role", role);
            session.setAttribute("departStation", new Station());
            session.setAttribute("arriveStation", new Station());
            request.setAttribute("mainMessage", "Stations management page");
            request.setAttribute("allStations", allStations);
            return request.getContextPath() + "/admin/stations.jsp";
        }
    }
}
