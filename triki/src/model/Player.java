package model;

import java.util.Date;

public class Player {
    private String name;
    private String status;
    private String figure;
    private Date dateTime;

    public Player(String name, String status, String figure, Date dateTime) {
        this.name = name;
        this.status = status;
        this.figure = figure;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
}
