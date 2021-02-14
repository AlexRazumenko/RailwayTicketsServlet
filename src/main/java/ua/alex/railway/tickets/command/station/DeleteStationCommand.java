package ua.alex.railway.tickets.command.station;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteStationCommand implements Command {

    private StationService stationService;

    public DeleteStationCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        stationService.deleteStation(id);

        String role = (String) request.getSession().getAttribute("role");
        request.setAttribute("allStations", stationService.getAllStations());
        request.setAttribute("mainMessage", "Stations management page");

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println("Context path: " + request.getContextPath());
            return request.getContextPath() + "/admin/stations.jsp";
        }
    }
}
