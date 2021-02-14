package ua.alex.railway.tickets.dao.impl;

import ua.alex.railway.tickets.dao.TrainDAO;
import ua.alex.railway.tickets.dao.mapper.TrainMapper;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import java.sql.*;
import java.util.*;

public class JdbcTrainDao implements TrainDAO {

    private Connection connection;

    public JdbcTrainDao() {
        try {
            this.connection = ConnectionPoolHolder.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Train train) {
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps =
                         connection.prepareStatement("INSERT INTO trains " +
                                 "(id, departtime, arrivetime, number, departstation_id, arrivestation_id, price) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, ConnectionPoolHolder.getAndUpdateMaxId());
                ps.setTime(2, Time.valueOf(train.getDepartTime()));
                ps.setTime(3, Time.valueOf(train.getArriveTime()));
                ps.setInt(4, train.getNumber());
                ps.setLong(5, train.getDepartStation().getId());
                ps.setLong(6, train.getArriveStation().getId());
                ps.setInt(7, train.getPrice());
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
    public Train findById(long id) {
        final String query =
                String.format(
                        "select * FROM trains INNER JOIN stations AS departstations ON departstations.id = trains.departstation_id " +
                                "INNER JOIN stations AS arrivestations ON arrivestations.id = trains.arrivestation_id " +
                                "WHERE trains.id = %d", id);
//                "select * from trains where id = " + id;
        Train train = null;

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            TrainMapper trainMapper = new TrainMapper();
            if (rs.next()) {
                train = trainMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return train;
    }

    @Override
    public List<Train> findAll() {
            final String query = "select * FROM trains INNER JOIN stations AS departstations ON departstations.id = trains.departstation_id " +
            "INNER JOIN stations AS arrivestations ON arrivestations.id = trains.arrivestation_id ";
        return getTrainsByQuery(query);
    }

//    SELECT * FROM trains INNER JOIN stations AS departstations ON departstations.id = trains.departstation_id
//     INNER JOIN stations AS arrivestations ON arrivestations.id = trains.arrivestation_id order by number

    @Override
    public void update(Train train) {
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps =
                         connection.prepareStatement(
                                 "UPDATE trains SET departtime = ?, arrivetime = ?, number =?, " +
                                         "departstation_id = ?, arrivestation_id = ?, price = ?" +
                                         "WHERE id= ?")) {
                ps.setObject(1, train.getDepartTime());
                ps.setObject(2, train.getArriveTime());
                ps.setInt(3, train.getNumber());
                ps.setLong(4, train.getDepartStation().getId());
                ps.setLong(5, train.getArriveStation().getId());
                ps.setInt(6, train.getPrice());
                ps.setLong(7, train.getId());
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
        final String query = "delete from trains where id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Train> findTrainsByDepartureStationId(long id) {
        final String query = String.format(
                "select * FROM trains INNER JOIN stations AS departstations ON departstations.id = trains.departstation_id " +
                        "INNER JOIN stations AS arrivestations ON arrivestations.id = trains.arrivestation_id " +
                        "WHERE trains.departstation_id = %d", id);
        return getTrainsByQuery(query);
    }

    @Override
    public List<Train> findTrainsByDepartureStationAndArriveStationId(long departureStationId, long arrivalStationId) {
        final String query = String.format(
                "select * FROM trains INNER JOIN stations AS departstations ON departstations.id = trains.departstation_id " +
                        "INNER JOIN stations AS arrivestations ON arrivestations.id = trains.arrivestation_id " +
                        "WHERE trains.departstation_id = %d AND trains.arrivestation_id = %d",
                departureStationId, arrivalStationId);
        return getTrainsByQuery(query);
    }

    private List<Train> getTrainsByQuery(String query) {
        Map<Long, Train> trains = new LinkedHashMap<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            TrainMapper trainMapper = new TrainMapper();
            while (rs.next()) {
                Train train = trainMapper.extractFromResultSet(rs);
                train = trainMapper.makeUnique(trains, train);
            }
            return new ArrayList<>(trains.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
