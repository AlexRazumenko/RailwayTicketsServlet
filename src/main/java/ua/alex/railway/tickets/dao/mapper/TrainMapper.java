package ua.alex.railway.tickets.dao.mapper;

import ua.alex.railway.tickets.dao.mapper.ObjectMapper;
import ua.alex.railway.tickets.entity.Station;
import ua.alex.railway.tickets.entity.Train;
import ua.alex.railway.tickets.service.StationService;
import ua.alex.railway.tickets.service.TrainService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainMapper implements ObjectMapper<Train> {

    private Map<Long, Station> stationList = new StationService().getAllStations()
            .stream().collect(Collectors.toMap(Station::getId, station -> station));

    @Override
    public Train extractFromResultSet(ResultSet rs) throws SQLException {
        Train train = new Train();
        train.setId(rs.getLong(1));
        train.setArriveTime(rs.getTime(2).toLocalTime());
        train.setDepartTime(rs.getTime(3).toLocalTime());
        train.setNumber(rs.getInt(4));
        train.setPrice(rs.getInt(7));
        train.setDepartStation(new Station(rs.getLong(8), rs.getString(9)));
        train.setArriveStation(new Station(rs.getLong(10), rs.getString(11)));
//        train.setDepartStation(stationList.get(rs.getLong("departstation_id")));
//        train.setArriveStation(stationList.get(rs.getLong("arrivestation_id")));

        System.out.println(train);
        return train;
    }

    @Override
    public Train makeUnique(Map<Long, Train> cache, Train train) {
        cache.putIfAbsent(train.getId(), train);
        return train;
    }
}
