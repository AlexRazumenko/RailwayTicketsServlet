package ua.alex.railway.tickets.dao.mapper;

import ua.alex.railway.tickets.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class StationMapper implements ObjectMapper<Station> {

    @Override
    public Station extractFromResultSet(ResultSet rs) throws SQLException {
        Station station = new Station();
        station.setId(rs.getLong("id"));
        station.setName(rs.getString("name"));
        return station;
    }

    @Override
    public Station makeUnique(Map<Long, Station> cache, Station station) {
            cache.putIfAbsent(station.getId(), station);
            return cache.get(station.getId());

    }
}
