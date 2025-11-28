package com.example.myapplication.models;

public class Ticket {
    private  int id;
    private String title;
    private String description;
    private String status;
    private String createdAt;
    private String owner;

    public Ticket(int id, String title, String description, String status, String createdAt, String owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.owner = owner;
    }

    public int getId() {return id;}
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getOwner() { return owner; }
}
