package com.example.spit_app.admin;

public class Announcements {
    private String announceid;
    private String dt;
    private String event;
    private String date;


    public Announcements(String announceid, String dt, String event, String date) {
        this.announceid = announceid;
        this.dt = dt;
        this.event = event;
        this.date=date;
    }

    public String getAnnounceid() {
        return announceid;
    }

    public String getDt() {
        return dt;
    }

    public String getEvent() {
        return event;
    }

    public String getDate(){ return date;}
}
