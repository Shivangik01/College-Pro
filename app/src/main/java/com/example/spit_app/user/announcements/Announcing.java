package com.example.spit_app.user.announcements;

public class Announcing {
    String announceid;
    String dt;
    String event;
    String date;

    public Announcing(){

    }

    public Announcing(String announceid, String dt, String event, String date) {
        this.announceid = announceid;
        this.dt = dt;
        this.event = event;
        this.date=date;
    }

    public void setDate(String date){this.date=date;}

    public void setEvent(String event){this.event=event; }


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
