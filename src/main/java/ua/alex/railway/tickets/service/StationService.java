package ua.alex.railway.tickets.service;

import ua.alex.railway.tickets.dao.StationDAO;
import ua.alex.railway.tickets.dao.factory.DAOFactory;
import ua.alex.railway.tickets.entity.Station;

import java.sql.SQLException;
import java.util.List;

public class StationService {
    private DAOFactory daoFactory;

    public StationService() {
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Station> getAllStations() {
        try (StationDAO dao = daoFactory.createStationDAO()){
            return dao.findAll();
        }
    }

    public Station findStationById(long id) {
        try (StationDAO dao = daoFactory.createStationDAO()){
            return dao.findById(id);
        }
    }

    public Station findStationByName(String name) {
        try (StationDAO dao = daoFactory.createStationDAO()){
            return dao.findByName(name);
        }
    }

    public void createStation(Station station) {
        try (StationDAO dao = daoFactory.createStationDAO()){
            dao.create(station);
        }
    }

    public void updateStation (Station station) {
        try (StationDAO dao = daoFactory.createStationDAO()){
            dao.update(station);
        }
    }

    public void deleteStation (long id) {
        try (StationDAO dao = daoFactory.createStationDAO()){
            dao.delete(id);
        }
    }

}
