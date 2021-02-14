package ua.alex.railway.tickets.command.station;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateStationCommand implements Command {

    private StationService stationService;

    public UpdateStationCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("ROLE_GUEST".equals(role)) {

            long id = Long.parseLong(request.getParameter("id"));
            String name = (String) request.getParameter("name");

            Station station = new Station(name);

            System.out.println(station);
            stationService.updateStation(station);

            session.setAttribute("mainMessage", "Station successfully updated.");
            request.setAttribute("allStations", stationService.getAllStations());

//            return request.getContextPath() + "/index.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
//            return "redirect:/WEB-INF/admin/adminPage.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
            System.out.println("else");
        }
        return request.getContextPath() + "/admin/stations.jsp";
    }
}
