package com.example.myapplication.timely;

public class ToDo {
    private int id;
    private String date, description;
    private long started, finished;

    public ToDo() {
    }

    public ToDo(int id, String date, String description, long started, long finished) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.started = started;
        this.finished = finished;
    }

    public ToDo(String date, String description, long started, long finished) {
        this.date = date;
        this.description = description;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}
