package ua.alex.railway.tickets.command.train;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.dto.TrainDTO;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateTrainCommand implements Command {

    private TrainService trainService;

    public UpdateTrainCommand(TrainService trainService) {
        this.trainService = trainService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("ROLE_GUEST".equals(role)) {

            long id = Long.parseLong(request.getParameter("id"));

            int number = Integer.parseInt(request.getParameter("number"));
            Station departStation = (Station) request.getAttribute("departStation");
            Station arriveStation = (Station) request.getAttribute("arriveStation");
//            long departStationId = Integer.parseInt(request.getParameter("departStationId"));
//            long arriveStationId = Integer.parseInt(request.getParameter("arriveStationId"));
            int departHour = Integer.parseInt(request.getParameter("departHour"));
            int arriveHour = Integer.parseInt(request.getParameter("arriveHour"));
            int departMinute = Integer.parseInt(request.getParameter("departMinute"));
            int arriveMinute = Integer.parseInt(request.getParameter("arriveMinute"));
            int price = Integer.parseInt(request.getParameter("price"));

//               public TrainDTO(int number, Station departStation, Station arriveStation, //Long id,
//            int departHour, int departMinute, int arriveHour, int arriveMinute, int price) { //

            TrainDTO trainDTO = new TrainDTO(number, departStation, arriveStation,
                    departHour, departMinute, arriveHour, arriveMinute, price  );
            trainService.updateTrain(trainDTO);

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
