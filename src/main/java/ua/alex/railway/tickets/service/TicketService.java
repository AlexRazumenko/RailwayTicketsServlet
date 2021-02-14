package ua.alex.railway.tickets.service;

import ua.alex.railway.tickets.dao.TicketDAO;
import ua.alex.railway.tickets.dao.factory.DAOFactory;
import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketService {

    private DAOFactory daoFactory;

    public TicketService() {
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllTickets() {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            return dao.findAll();
        }
    }

    public Ticket findTicketById(long id) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            return dao.findById(id);
        }
    }

    public List<TicketDTO> findByTrainAndDepartDate(Long trainId, LocalDate departDate) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            return dao.findByTrainAndDepartDate(trainId, departDate);
        }
    }

    public List<Integer> findFreeSeatsNumbersByTrainAndDepartDate (Long trainId, LocalDate departDate) {
//        List<Ticket> l1 = dao.findOccupiedSeatsNumbersByTrainAndDepartDate(trainId, departDate);
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            List<Integer> l1 = dao.findOccupiedSeatsNumbersByTrainAndDepartDate(trainId, departDate);
            System.out.println("Ocupied: " + l1);

            List<Integer> freeSeatsList =
                    IntStream.rangeClosed(1,20).boxed().collect(Collectors.toList());
                    freeSeatsList.removeAll(dao.findOccupiedSeatsNumbersByTrainAndDepartDate(trainId, departDate));
            System.out.println("Free: " + freeSeatsList);
            return freeSeatsList;
        }
    }


    public List<TicketDTO> findByTrainAndDepartDateAndOccupied(Train train, LocalDate departDate, boolean isOccupied) {
        System.out.println("In service Train id: " + train.getId() + " Date: " + departDate + " Is occupied: " + isOccupied );
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            return dao.findByTrainAndDepartDateAndOccupied(train, departDate, isOccupied);
        }
    }


    public List<TicketDTO> findTicketsByUserOrderByDepartDate (User user) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            return dao.findByUserOrderByDepartDate(user);
        }
    }

    public List<Ticket> findTicketsByUserOrderByTrain (User user) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            return dao.findByUserOrderByTrain(user);
        }
    }

    public void createTicket(Ticket ticket) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            dao.create(ticket);
        }
    }

    public void updateTicket (Ticket ticket) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            dao.update(ticket);
        }
    }

    public void deleteTicket (long id) {
        try (TicketDAO dao = daoFactory.createTicketDAO()){
            dao.delete(id);
        }
    }

    public List<TicketDTO> fillTrainWithTickets (Train train, LocalDate date, User user) {
        List<Ticket> ticketList = new LinkedList<>();
        Ticket ticket = new Ticket(train, date, 0, false, null);

        try (TicketDAO dao = daoFactory.createTicketDAO()){
            for (int i = 1; i < 21; i++) {
                ticket.setPlace(i);
                dao.create(ticket);
                ticketList.add(ticket);
            }
        }
        return ticketList.stream().map(ticket1 ->
                new TicketDTO (ticket.getId(), ticket.getDepartDate(), ticket.getPlace(), ticket.isOccupied(),
                        train.getDepartTime(), train.getArriveTime(), train.getNumber(), train.getPrice(),
                        train.getArriveStation().getName(), train.getArriveStation().getName(),
                        user.getEmail(), user.getFirstName(), user.getLastName())).collect(Collectors.toList());
    }
}
