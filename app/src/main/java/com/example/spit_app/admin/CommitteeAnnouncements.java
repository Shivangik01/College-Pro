package com.example.spit_app.admin;

public class CommitteeAnnouncements {
    private String announceid;
    private String data;
    private String event;
    private String date;
    private String name;

    public CommitteeAnnouncements() {
    }

    public CommitteeAnnouncements(String announceid, String data, String event, String name, String date) {
        this.announceid = announceid;
        this.data = data;
        this.event = event;
        this.date=date;
        this.name=name;
    }

    public String getAnnounceid() {
        return announceid;
    }

    public String getDt() {
        return data;
    }

    public String getEvent() {
        return event;
    }

    public String getDate(){ return date;}

    public String getName(){ return name;}
}
