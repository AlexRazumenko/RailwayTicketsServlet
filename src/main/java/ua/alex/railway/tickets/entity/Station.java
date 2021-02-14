package ua.alex.railway.tickets.entity;

import java.util.ArrayList;
import java.util.List;

public class Station {

    private Long id;
    private String name;
    private List<Train> trainsArriving = new ArrayList<>();
    private List<Train> trainsDeparting = new ArrayList<>();

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public Station(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(Long id, String name, List<Train> trainsArriving, List<Train> trainsDeparting) {
        this.id = id;
        this.name = name;
        this.trainsArriving = trainsArriving;
        this.trainsDeparting = trainsDeparting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Train> getTrainsArriving() {
        return trainsArriving;
    }

    public void setTrainsArriving(List<Train> trainsArriving) {
        this.trainsArriving = trainsArriving;
    }

    public List<Train> getTrainsDeparting() {
        return trainsDeparting;
    }

    public void setTrainsDeparting(List<Train> trainsDeparting) {
        this.trainsDeparting = trainsDeparting;
    }

    @Override
    public String toString() {
        return name;
    }
}
