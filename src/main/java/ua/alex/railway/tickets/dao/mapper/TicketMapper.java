package ua.alex.railway.tickets.dao.mapper;

import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.service.TrainService;
import ua.alex.railway.tickets.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TicketMapper implements ObjectMapper<Ticket> {
    private UserService userService;
    private TrainService trainService;

//    Long id, LocalDate departureDate, int place, boolean occupied,
//    LocalTime arriveTime, LocalTime departTime, int trainNumber, int price,
//    String departStationName, String arriveStationName, String email, String passangerFirstName,
//    String passangerLastName
//



    @Override
    public Ticket extractFromResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        ticket.setDepartDate(rs.getDate("departure_date").toLocalDate());
        ticket.setPlace(rs.getInt("place"));
        ticket.setTrain(trainService.findTrainById(rs.getLong("train_id")));
        ticket.setUser(userService.findUserById(rs.getLong("user_id")));
        ticket.setOccupied(rs.getBoolean("occupied"));
        return ticket;
    }

    @Override
    public Ticket makeUnique(Map<Long, Ticket> cache, Ticket ticket) {
        cache.putIfAbsent(ticket.getId(), ticket);
        return cache.get(ticket.getId());
    }
}
