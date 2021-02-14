package ua.alex.railway.tickets.command.train;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.dto.TrainDTO;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.service.StationService;
import ua.alex.railway.tickets.service.TrainService;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddTrainCommand  implements Command {

    private TrainService trainService;
    private StationService stationService;

    public AddTrainCommand(TrainService trainService, StationService stationService) {
        this.trainService = trainService;
        this.stationService = stationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

//        System.out.println(request.getParameterValues(""));

        if ("ROLE_GUEST".equals(role)) {

//            TrainDTO newTrainDTO = (TrainDTO) session.getAttribute("newTrainDTO");
//            System.out.println("TrainDTO: " + newTrainDTO);
//
            int number = Integer.parseInt(request.getParameter("number"));
//            Station departStation = (Station) session.getAttribute("departStation");
//            Station arriveStation = (Station) session.getAttribute("arriveStation");
//            int departStationId = Integer.parseInt(request.getParameter("departStationId"));
            Station departStation = stationService.findStationById(Integer.parseInt(request.getParameter("departStationId")));
            Station arriveStation = stationService.findStationById(Integer.parseInt(request.getParameter("arriveStationId")));
            int departHour = Integer.parseInt(request.getParameter("departHour"));
            int arriveHour = Integer.parseInt(request.getParameter("arriveHour"));
            int departMinute = Integer.parseInt(request.getParameter("departMinute"));
            int arriveMinute = Integer.parseInt(request.getParameter("arriveMinute"));
            int price = Integer.parseInt(request.getParameter("price"));

//               public TrainDTO(int number, Station departStation, Station arriveStation, //Long id,
//            int departHour, int departMinute, int arriveHour, int arriveMinute, int price) { //
//            TrainDTO
             TrainDTO newTrainDTO = new TrainDTO(number, departStation, arriveStation,
                    departHour, departMinute, arriveHour, arriveMinute, price);
            System.out.println("TrainDTO: " + newTrainDTO);
            trainService.createTrain(newTrainDTO);

            session.setAttribute("mainMessage", "Train successfully created.");
            request.setAttribute("allTrains", trainService.getAllTrains());

//            return request.getContextPath() + "/index.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
//            return "redirect:/WEB-INF/admin/adminPage.jsp";
//            return "redirect:/WEB-INF/user/userPage.jsp";
            System.out.println("else");
        }
        return request.getContextPath() + "/admin/trains.jsp";
    }

}
