package ua.alex.railway.tickets.dto;

import ua.alex.railway.tickets.entity.Station;

import java.time.LocalTime;

public class TrainDTO {

//    private Long id;
    private int number;
    private Station departStation;
    private Station arriveStation;
//    private long departStationId;
//    private long arriveStationId;
    private int departHour;
    private int departMinute;
    private int arriveHour;
    private int arriveMinute;

    private int price;

    public TrainDTO() {
    }

    public TrainDTO(int number, Station departStation, Station arriveStation, //Long id,
                    int departHour, int departMinute, int arriveHour, int arriveMinute, int price) { //
//        this.id = id;
        this.number = number;
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.departHour = departHour;
        this.departMinute = departMinute;
        this.arriveHour = arriveHour;
        this.arriveMinute = arriveMinute;
        this.price = price;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

//    public long getDepartStationId() {
//        return departStationId;
//    }
//
//    public void setDepartStationId(long departStationId) {
//        this.departStationId = departStationId;
//    }
//
//    public long getArriveStationId() {
//        return arriveStationId;
//    }
//
//    public void setArriveStationId(long arriveStationId) {
//        this.arriveStationId = arriveStationId;
//    }



    //
    public Station getDepartStation() {
        return departStation;
    }

    public void setDepartStation(Station departStation) {
        this.departStation = departStation;
    }

    public Station getArriveStation() {
        return arriveStation;
    }

    public void setArriveStation(Station arriveStation) {
        this.arriveStation = arriveStation;
    }

    //    public int getDepartStationId() {
//        return departStationId;
//    }
//
//    public void setDepartStationId(int departStationId) {
//        this.departStationId = departStationId;
//    }
//
//    public int getArriveStationId() {
//        return arriveStationId;
//    }
//
//    public void setArriveStationId(int arriveStationId) {
//        this.arriveStationId = arriveStationId;
//    }

    public int getDepartHour() {
        return departHour;
    }

    public void setDepartHour(int departHour) {
        this.departHour = departHour;
    }

    public int getDepartMinute() {
        return departMinute;
    }

    public void setDepartMinute(int departMinute) {
        this.departMinute = departMinute;
    }

    public int getArriveHour() {
        return arriveHour;
    }

    public void setArriveHour(int arriveHour) {
        this.arriveHour = arriveHour;
    }

    public int getArriveMinute() {
        return arriveMinute;
    }

    public void setArriveMinute(int arriveMinute) {
        this.arriveMinute = arriveMinute;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
                "number=" + number +
                ", departStation=" + departStation +
                ", arriveStation=" + arriveStation +
                ", departHour=" + departHour +
                ", departMinute=" + departMinute +
                ", arriveHour=" + arriveHour +
                ", arriveMinute=" + arriveMinute +
                ", price=" + price +
                '}';
    }
}
