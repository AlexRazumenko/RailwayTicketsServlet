package ua.alex.railway.tickets.dao;

import ua.alex.railway.tickets.entity.Train;

import java.util.List;

public interface TrainDAO extends GenericDAO<Train> {

    List<Train> findTrainsByDepartureStationId (long id);
    List<Train> findTrainsByDepartureStationAndArriveStationId (long departureStationId, long arrivalStationId);
}
