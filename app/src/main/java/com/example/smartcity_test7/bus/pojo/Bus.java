package com.example.smartcity_test7.bus.pojo;

import java.util.List;

public class Bus {


    /**
     * id : 1
     * name : 一号线
     * first : 光谷金融街
     * end : 南湖大厦
     * startTime : 6:30
     * price : 8
     * mileage : 20
     */

    private int id;
    private String name;
    private String first;
    private String end;
    private String startTime;
    private int price;
    private String mileage;
    private List<Station> stations;

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Bus(int id, String name, String first, String end, String startTime, int price, String mileage, List<Station> stations) {
        this.id = id;
        this.name = name;
        this.first = first;
        this.end = end;
        this.startTime = startTime;
        this.price = price;
        this.mileage = mileage;
        this.stations = stations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
