package ua.alex.railway.tickets.command.train;

import ua.alex.railway.tickets.command.Command;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TrainCommand implements Command {

    private TrainService trainService;

    public TrainCommand(TrainService trainService) {
        this.trainService = trainService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        long id = Long.parseLong(request.getParameter("trainId"));
        Train train = trainService.findTrainById(id);

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/WEB-INF/admin/adminPage.jsp";
        } else  {//if (dbUser.getRole() == RoleType.ROLE_USER)
            System.out.println(role);
            System.out.println(train);
            session.setAttribute("role", role);
            request.setAttribute("train", train);
            return request.getContextPath() + "/user/train.jsp";
        }
    }
}
