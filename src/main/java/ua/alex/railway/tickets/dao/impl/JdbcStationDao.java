package ua.alex.railway.tickets.dao.impl;

import ua.alex.railway.tickets.dao.StationDAO;
import ua.alex.railway.tickets.dao.mapper.StationMapper;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import java.sql.*;
import java.util.*;

public class JdbcStationDao implements StationDAO {

    private Connection connection;

    public JdbcStationDao() {
        try {
            this.connection = ConnectionPoolHolder.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Station station) {

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps =
                         connection.prepareStatement
                                 ("INSERT INTO stations (id, name) VALUES (?, ?)",
                                         Statement.RETURN_GENERATED_KEYS)) {
                ps.setLong(1, ConnectionPoolHolder.getAndUpdateMaxId());
                ps.setString(2, station.getName());
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
    public Station findById(long id) {
        final String query = "select * from stations where id = " + id;
        Station station = null;

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            StationMapper stationMapper = new StationMapper();

            if (rs.next()) {
                station = stationMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return station;
    }

    @Override
    public Station findByName(String name) {
        final String query = "select * from stations where name = " + name;
        Station station = null;

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(query);
            StationMapper stationMapper = new StationMapper();

            if (rs.next()) {
                station = stationMapper.extractFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return station;
    }

    @Override
    public List<Station> findAll() {
        Map<Long, Station> stations = new HashMap<>();

        final String query = "select * from stations";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);

            StationMapper stationMapper = new StationMapper();

            while (rs.next()) {
                Station station = stationMapper.extractFromResultSet(rs);
                station = stationMapper.makeUnique(stations, station);
            }
            return new ArrayList<>(stations.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void update(Station station) {
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps =
                         connection.prepareStatement("INSERT INTO stations (id, name) VALUES (?, ?)")) {
                ps.setLong(1, station.getId());
                ps.setString(2,station.getName());
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
            final String query = "delete from stations where id = "+ id;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
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
