package ua.alex.railway.tickets.entity;

import java.time.LocalTime;

public class Train {
    private long id;
    private int number;
//    private int departStationId;
//    private int arriveStationId;
    private Station departStation;
    private Station arriveStation;
    private LocalTime departTime;
    private LocalTime arriveTime;
    private int price;

    public Train() {
    }

    public Train(long id, int number, Station departStation, Station arriveStation,
                 LocalTime departTime, LocalTime arriveTime, int price) {
        this.id = id;
        this.number = number;
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

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

    public LocalTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Train{" +
                "number=" + number +
                ", departStation=" + departStation +
                ", arriveStation=" + arriveStation +
                ", departTime=" + departTime +
                ", arriveTime=" + arriveTime +
//                ", price=" + price +
                '}';
    }
}
