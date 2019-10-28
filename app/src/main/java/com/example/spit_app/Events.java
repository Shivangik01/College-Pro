package com.example.spit_app;

class Events {

    String EVENT,TIME,DATE,MONTH,YEAR;

    public Events(String EVENT, String TIME, String DATE, String MONTH, String YEAR) {
        this.EVENT = EVENT;
        this.TIME = TIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
    }

    public String getEVENT() {
        return EVENT;
    }

    public String getTIME() {
        return TIME;
    }

    public String getDATE() {
        return DATE;
    }

    public String getMONTH() {
        return MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }
}
