package ua.alex.railway.tickets.servlet;

import ua.alex.railway.tickets.command.*;
import ua.alex.railway.tickets.command.station.AddStationCommand;
import ua.alex.railway.tickets.command.station.DeleteStationCommand;
import ua.alex.railway.tickets.command.station.StationListCommand;
import ua.alex.railway.tickets.command.station.UpdateStationCommand;
import ua.alex.railway.tickets.command.ticket.BookTicketCommand;
import ua.alex.railway.tickets.command.train.*;
import ua.alex.railway.tickets.service.StationService;
import ua.alex.railway.tickets.service.TicketService;
import ua.alex.railway.tickets.service.TrainService;
import ua.alex.railway.tickets.service.UserService;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "MainServlet", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    private Connection connection;
    private UserService userService = new UserService();
    private StationService stationService = new StationService();
    private TrainService trainService = new TrainService();
    private TicketService ticketService = new TicketService();
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        try {
            connection = ConnectionPoolHolder.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

//        servletConfig.getServletContext().getSession
//        commands.put("userList", new UsersListCommand(userService));

        commands.put("registration", new RegistrationCommand(userService));
        commands.put("loginPage", new LoginPageCommand());
        commands.put("login", new LoginCommand(userService));
        commands.put("timetable", new TimetablePageCommand(stationService));

        commands.put("user/registrationPage", new RegistrationPageCommand());
        commands.put("user/userPage", new UserPageCommand(userService));
        commands.put("user/users", new UserListCommand(userService));
        commands.put("user/deleteUser", new DeleteUserCommand(userService));

        commands.put("admin/addStation", new AddStationCommand(stationService));
        commands.put("admin/stations", new StationListCommand(stationService));
        commands.put("admin/deleteStation", new DeleteStationCommand(stationService));
        commands.put("admin/updateStation", new UpdateStationCommand(stationService));

        commands.put("admin/addTrain", new AddTrainCommand(trainService, stationService));
        commands.put("admin/trains", new TrainListCommand(trainService, stationService));
        commands.put("admin/deleteTrain", new DeleteTrainCommand(trainService));
        commands.put("admin/updateTrain", new UpdateTrainCommand(trainService));
        commands.put("user/trainsByTwoStations", new TrainsByDepAndArrStationCommand(trainService, stationService));
        commands.put("user/trainsByOneStation", new TrainsByDepStationCommand(trainService, stationService));

        commands.put("user/trainPage", new TrainCommand(trainService));
        commands.put("user/freeSeats", new TrainFreeSeatsCommand(trainService, ticketService));
        commands.put("user/bookTicket", new BookTicketCommand(ticketService, trainService));

    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        processRequest(httpServletRequest, httpServletResponse);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String path = request.getRequestURI();

path = path.replaceFirst("/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path, req -> "/index.jsp");
        String page = command.execute(request);
        System.out.println(page);

//        request.setAttribute("message", "Users page");
        if (page.contains("redirect:")) {
            String address = page.replace("redirect:", "");
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
