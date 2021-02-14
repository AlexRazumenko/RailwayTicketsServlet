package ua.alex.railway.tickets.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDTO {

    private Long id;
    private LocalDate departureDate;
    private int place;
    private  boolean occupied;
    private LocalTime arriveTime;
    private LocalTime departTime;
    private int trainNumber;
    private int price;
    private String departStationName;
    private String arriveStationName;
    private String email;
    private String passengerFirstName;
    private String passengerLastName;

    public TicketDTO() {
    }

    public TicketDTO(Long id, LocalDate departureDate, int place, boolean occupied,
                     LocalTime arriveTime, LocalTime departTime, int trainNumber, int price,
                     String departStationName, String arriveStationName, String email, String passengerFirstName,
                     String passengerLastName) {
        this.id = id;
        this.departureDate = departureDate;
        this.place = place;
        this.occupied = occupied;
        this.arriveTime = arriveTime;
        this.departTime = departTime;
        this.trainNumber = trainNumber;
        this.price = price;
        this.departStationName = departStationName;
        this.arriveStationName = arriveStationName;
        this.email = email;
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public LocalTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDepartStationName() {
        return departStationName;
    }

    public void setDepartStationName(String departStationName) {
        this.departStationName = departStationName;
    }

    public String getArriveStationName() {
        return arriveStationName;
    }

    public void setArriveStationName(String arriveStationName) {
        this.arriveStationName = arriveStationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "id=" + id +
                ", departureDate=" + departureDate +
                ", place=" + place +
                ", occupied=" + occupied +
                ", arriveTime=" + arriveTime +
                ", departTime=" + departTime +
                ", trainNumber=" + trainNumber +
                ", price=" + price +
                ", departStationName='" + departStationName + '\'' +
                ", arriveStationName='" + arriveStationName + '\'' +
                ", email='" + email + '\'' +
                ", passengerFirstName='" + passengerFirstName + '\'' +
                ", passengerLastName='" + passengerLastName + '\'' +
                '}';
    }
}
