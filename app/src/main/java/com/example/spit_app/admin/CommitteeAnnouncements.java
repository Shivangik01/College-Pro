package com.example.spit_app.admin;

public class CommitteeAnnouncements {
    String announceid;
    String dt;
    String event;
    String date;
    String name;

    public CommitteeAnnouncements(){

    }

    public CommitteeAnnouncements(String announceid, String dt, String event,String name, String date) {
        this.announceid = announceid;
        this.dt = dt;
        this.event = event;
        this.date=date;
        this.name=name;
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

    public String getNam(){ return name;}
}
