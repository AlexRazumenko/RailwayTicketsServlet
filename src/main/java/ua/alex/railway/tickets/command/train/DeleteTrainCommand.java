package ua.alex.railway.tickets.command.train;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;

public class DeleteTrainCommand implements Command {

    private TrainService trainService;

    public DeleteTrainCommand(TrainService trainService) {
        this.trainService = trainService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        trainService.deleteTrain(id);

        String role = (String) request.getSession().getAttribute("role");
        request.setAttribute("allTrains",trainService.getAllTrains());
        request.setAttribute("mainMessage", "Trains management page");

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println("Context path: " + request.getContextPath());
            return request.getContextPath() + "/admin/trains.jsp";
        }
    }


}
