package ua.alex.railway.tickets.entity;

import java.time.LocalDate;

public class Ticket {

    private Long id;
    private Train train;
    private LocalDate departDate;
    private int place;
    private boolean isOccupied;
    private User user;

    public Ticket() {
    }

    public Ticket(Train train, LocalDate departDate, int place, boolean isOccupied, User user) {
        this.train = train;
        this.departDate = departDate;
        this.place = place;
        this.isOccupied = isOccupied;
        this.user = user;
    }

    public Ticket(Long id, Train train, LocalDate departDate, int place, boolean isOccupied, User user) {
        this.id = id;
        this.train = train;
        this.departDate = departDate;
        this.place = place;
        this.isOccupied = isOccupied;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public LocalDate getDepartDate() {
        return departDate;
    }

    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
