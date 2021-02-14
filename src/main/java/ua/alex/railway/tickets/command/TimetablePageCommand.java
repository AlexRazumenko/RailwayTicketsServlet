package ua.alex.railway.tickets.command;

import ua.alex.railway.tickets.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TimetablePageCommand implements Command {

    private StationService stationService;

    public TimetablePageCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
//
//        HttpSession session = request.getSession();
//        String role = (String) session.getAttribute("role");
//
//        if (!"ROLE_GUEST".equals(role)) {
//            return "redirect:/index.jsp";
//        } else  {
request.setAttribute("allStations", stationService.getAllStations());
            return request.getContextPath() + "/timetable.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";

    }
}
