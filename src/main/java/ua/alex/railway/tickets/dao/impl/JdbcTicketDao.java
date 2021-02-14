package ua.alex.railway.tickets.dao.impl;

import ua.alex.railway.tickets.dao.TicketDAO;
import ua.alex.railway.tickets.dao.mapper.TicketDTOMapper;
import ua.alex.railway.tickets.dao.mapper.TicketMapper;
import ua.alex.railway.tickets.dto.TicketDTO;
import ua.alex.railway.tickets.entity.Ticket;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.entity.User;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;
import ua.alex.railway.tickets.utils.Utils;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class JdbcTicketDao implements TicketDAO {

    private Connection connection;

    public JdbcTicketDao() {
        try {
            this.connection = ConnectionPoolHolder.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Ticket ticket) {

        try {
            connection.setAutoCommit(false);
            Long id = ticket.getUser().getId();

            try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO tickets (id, train_id, user_id, departure_date, place, occupied) " +
                                    "VALUES (?, ?, ?, ?, ?, ?)",
                                         Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, ConnectionPoolHolder.getAndUpdateMaxId());
                ps.setLong(2, ticket.getTrain().getId());
                ps.setLong(3, id == 0 ? 0 : ticket.getUser().getId());
                ps.setDate(4, java.sql.Date.valueOf(ticket.getDepartDate()));
                ps.setInt(5, ticket.getPlace());
                ps.setBoolean(6, ticket.isOccupied());
                ps.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Ticket findById(long id) {
        final String query = "select * from tickets where id = " + id;
        Ticket ticket = null;

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            TicketMapper ticketMapper = new TicketMapper();

            if (rs.next()) {
                ticket = ticketMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
       return getListByQuery("select * from tickets");
    }

    @Override
    public void update(Ticket ticket) {
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement
                    ("INSERT INTO tickets (id, train_id, user_id, departure_date, place, occupied) " +
                            "VALUES (?, ?, ?, ?, ?, ?)")) {
                ps.setLong(1, ticket.getId());
                ps.setLong(2, ticket.getTrain().getId());
                ps.setLong(3, ticket.getUser().getId());
                ps.setDate(4, java.sql.Date.valueOf(ticket.getDepartDate()));
                ps.setInt(5, ticket.getPlace());
                ps.setBoolean(6, ticket.isOccupied());
                ps.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        final String query = "delete from tickets where id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TicketDTO> findByTrainAndDepartDate(Long trainId, LocalDate departDate) {
        final String query = String.format("SELECT tk.id, " +
                        "tk.departure_date, " +
                        "tk.place, " +
                        "tk.occupied, " +
                        "tr.arrivetime, " +
                        "tr.departtime, " +
                        "tr.number, " +
                        "tr.price, " +
                        "ds.name AS ds_name, " +
                        "ars.name AS ars_name " +
                        "FROM tickets tk INNER JOIN trains AS tr ON tr.id = tk.train_id " +
                        "INNER JOIN stations AS ds ON ds.id = tr.departstation_id " +
                        "INNER JOIN stations AS ars ON ars.id = tr.arrivestation_id " +
                "WHERE tr.id = %d AND tk.departure_date = '" + Date.valueOf(departDate)+"'",
                trainId);
            return getDtoListByQuery(query);
    }

    @Override
    public List<Integer> findOccupiedSeatsNumbersByTrainAndDepartDate (Long trainId, LocalDate departDate) {
        System.out.println("Train ID: " + trainId + ", Date: " + departDate);
        final String query = String.format ("SELECT tk.place FROM tickets tk " +
                "WHERE tk.train_id = %d AND tk.departure_date = '" + Date.valueOf(departDate) +"'", trainId);

        List<Integer> occupiedTicketsList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                while (rs.next()) {
                    occupiedTicketsList.add(rs.getInt(1));
                }
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupiedTicketsList;
    }

    @Override
    public List<TicketDTO> findByTrainAndDepartDateAndOccupied(Train train, LocalDate departDate, boolean isOccupied) {
        System.out.println("IN DAO Train id: " + train.getId() + " Date: " + departDate + " Is occupied: " + isOccupied );
        final String query = String.format("SELECT tk.id, " +
                        "tk.departure_date, " +
                        "tk.place, " +
                        "tk.occupied, " +
                        "tr.arrivetime, " +
                        "tr.departtime, " +
                        "tr.number, " +
                        "tr.price, " +
                        "ds.name AS ds_name, " +
                        "ars.name AS ars_name " +
                        "FROM tickets tk INNER JOIN trains AS tr ON tr.id = tk.train_id " +
                        "INNER JOIN stations AS ds ON ds.id = tr.departstation_id " +
                        "INNER JOIN stations AS ars ON ars.id = tr.arrivestation_id " +
                        "WHERE tr.id = %d AND tk.departure_date = '" + Date.valueOf(departDate)+"'",
                train.getId(), isOccupied);
//        System.out.println("Train id: " + trainId + " Date: " + departDate + " Is occupied: " + isOccupied );
        List<TicketDTO> list = getDtoListByQuery(query);
//        if (list.isEmpty()) fillTrainWithTickets(train, departDate, Utils.getEmptyUser());
        list.forEach(System.out::println);
        return getDtoListByQuery(query);
    }

    @Override
    public List<TicketDTO> findByUserOrderByDepartDate(User user) {
//        final String query = String.format("SELECT * FROM tickets INNER JOIN users ON user,id = tickets.user_id " +
//                "WHERE user.id = %d ORDER BY tickets.departure_date DESC", user.getId());
//        return getListByQuery(query);
        final String query = String.format("SELECT tk.id, " +
                        "tk.departure_date, " +
                        "tk.place, " +
                        "tk.occupied, " +
                        "tr.arrivetime, " +
                        "tr.departtime, " +
                        "tr.number, " +
                        "tr.price, " +
                        "ds.name AS ds_name, " +
                        "ars.name AS ars_name " +
                        "FROM tickets tk INNER JOIN trains AS tr ON tr.id = tk.train_id " +
                        "INNER JOIN stations AS ds ON ds.id = tr.departstation_id " +
                        "INNER JOIN stations AS ars ON ars.id = tr.arrivestation_id " +
                        "WHERE tk.user_id = %d",
                user.getId());
        return getDtoListByQuery(query);
    }

    @Override
    public List<Ticket> findByUserOrderByTrain(User user) {
        final String query = String.format("SELECT * FROM tickets INNER JOIN users ON user,id = tickets.user_id " +
                "INNER JOIN trains WHERE user.id = %d ORDER BY tickets.train_id", user.getId());
        return getListByQuery(query);
    }

    private List<Ticket> getListByQuery (String query) {
        Map<Long, Ticket> tickets = new HashMap<>();
        try {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                TicketMapper ticketMapper = new TicketMapper();

                while (rs.next()) {
                    Ticket ticket = ticketMapper.extractFromResultSet(rs);
                    ticket = ticketMapper.makeUnique(tickets, ticket);
                }
                return new ArrayList<>(tickets.values());
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private List<TicketDTO> getDtoListByQuery (String query) {
        Map<Long, TicketDTO> tickets = new HashMap<>();
        List<TicketDTO> ticketList = Collections.emptyList();
        try {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(query);

                TicketDTOMapper ticketMapper = new TicketDTOMapper();

                while (rs.next()) {
                    System.out.println(rs);
                    TicketDTO ticketDto = ticketMapper.extractFromResultSet(rs);
                    ticketDto = ticketMapper.makeUnique(tickets, ticketDto);
                    System.out.println(ticketDto);
                }
                ticketList = new ArrayList<>(tickets.values());
                System.out.println(ticketList);
                return ticketList;
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

//    public void fillTrainWithTickets (Train train, LocalDate date, User user) {
//        List<Ticket> ticketList = new LinkedList<>();
//        Ticket ticket = new Ticket(train, date, 0, false, user);
//            for (int i = 1; i < 21; i++) {
//                ticket.setPlace(i);
//                create(ticket);
//                ticketList.add(ticket);
//            }

//        return ticketList.stream().map(ticket1 ->
//                new TicketDTO (ticket.getId(), ticket.getDepartDate(), ticket.getPlace(), ticket.isOccupied(),
//                        train.getDepartTime(), train.getArriveTime(), train.getNumber(), train.getPrice(),
//                        train.getArriveStation().getName(), train.getArriveStation().getName(),
//                        user.getEmail(), user.getFirstName(), user.getLastName())).collect(Collectors.toList());
//    }


    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
