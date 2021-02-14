package ua.alex.railway.tickets.service;

import ua.alex.railway.tickets.dao.TrainDAO;
import ua.alex.railway.tickets.dao.factory.DAOFactory;
import ua.alex.railway.tickets.dto.TrainDTO;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.utils.ConnectionPoolHolder;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class TrainService {

    private DAOFactory daoFactory;

    public TrainService() {
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Train> getAllTrains() {
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            return dao.findAll();
        }
    }

    public Train findTrainById(long id) {
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            return dao.findById(id);
        }
    }

    public List<Train> findTrainsByDepartureStationId (long id){
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            return dao.findTrainsByDepartureStationId(id);
        }
    }

    public List<Train> findTrainsByDepartureStationAndArriveStationId (long departureStationId, long arrivalStationId) {
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            return dao.findTrainsByDepartureStationAndArriveStationId(departureStationId, arrivalStationId);
        }
    }

    public void createTrain(TrainDTO trainDTO) {
        Train train = convertTrainDtoToTrain(trainDTO);
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            dao.create(train);
        }
    }

    public void updateTrain (TrainDTO trainDTO) {
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            Train train = convertTrainDtoToTrain(trainDTO);
            dao.update(train);
        }
    }

    public void deleteTrain (int id) {
        try (TrainDAO dao = daoFactory.createTrainDAO()){
            dao.delete(id);
        }
    }



    //    public Train(Long id, int number, Station departStation, Station arriveStation,
//                 LocalTime departTime, LocalTime arriveTime, int price) {

    private Train convertTrainDtoToTrain(TrainDTO trainDTO) {
        System.out.println("TrainDTO in convert: " + trainDTO);
        return new Train (
                ConnectionPoolHolder.getAndUpdateMaxId(),
                trainDTO.getNumber(),
                trainDTO.getDepartStation(),
                trainDTO.getArriveStation(),
                LocalTime.of(trainDTO.getDepartHour(), trainDTO.getDepartMinute()),
                LocalTime.of(trainDTO.getArriveHour(), trainDTO.getArriveMinute()),
                trainDTO.getPrice()
        );
    }


}
