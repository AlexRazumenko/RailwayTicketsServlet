package ua.alex.railway.tickets.dao.mapper;

import ua.alex.railway.tickets.dto.TicketDTO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

public class TicketDTOMapper implements ObjectMapper<TicketDTO> {

    //    Long id, LocalDate departureDate, int place, boolean occupied,
//    LocalTime arriveTime, LocalTime departTime, int trainNumber, int price,
//    String departStationName, String arriveStationName, String email, String passangerFirstName,
//    String passangerLastName
//

    @Override
    public TicketDTO extractFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i < rsmd.getColumnCount(); i++) {
            System.out.println(rsmd.getColumnName(i));       }


        TicketDTO ticketDto = new TicketDTO();
        ticketDto.setId((rs.getLong("id")));
        ticketDto.setDepartureDate(rs.getDate("departure_date").toLocalDate());
        ticketDto.setPlace(rs.getInt("place"));
        ticketDto.setOccupied(rs.getBoolean("occupied"));
        ticketDto.setArriveTime(rs.getTime("arrivetime").toLocalTime());
        ticketDto.setDepartTime(rs.getTime("departtime").toLocalTime());
        ticketDto.setTrainNumber(rs.getInt("number"));
        ticketDto.setPrice(rs.getInt("price"));
        ticketDto.setArriveStationName(rs.getString("ars_name"));
        ticketDto.setDepartStationName(rs.getString("ds_name"));
        return ticketDto;
    }

    @Override
    public TicketDTO makeUnique(Map<Long, TicketDTO> cache, TicketDTO ticketDTO) {
        cache.putIfAbsent(ticketDTO.getId(), ticketDTO);
        return cache.get(ticketDTO.getId());
    }
}
