package ua.alex.railway.tickets.dao;

import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket> {

    List<TicketDTO> findByTrainAndDepartDate (Long id, LocalDate departDate);

    List<TicketDTO> findByTrainAndDepartDateAndOccupied (Train train, LocalDate departDate, boolean isOccupied);

    List<TicketDTO> findByUserOrderByDepartDate(User user);

    List<Ticket> findByUserOrderByTrain (User user);

    List<Integer> findOccupiedSeatsNumbersByTrainAndDepartDate (Long trainId, LocalDate departDate);



}
