package ua.alex.railway.tickets.dao;

import ua.alex.railway.tickets.entity.Station;

public interface StationDAO extends GenericDAO<Station> {

    Station findByName (String name);

}
