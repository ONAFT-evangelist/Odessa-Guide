package com.example.georg.odessaguide.pojo;

import java.util.ArrayList;

public class Beach {
    private String day4;
    private String day3;
    private String day2;
    private String descr;
    private String duration;
    private String day1;
    private String cap;
    private String pic;
    private String title;
    private ArrayList<String> location;

    public Beach() {
    }

    public Beach(String day4, String day3, String day2, String descr, String duration, String day1, String cap, String pic, String title , ArrayList<String> location) {
        this.day4 = day4;
        this.day3 = day3;
        this.day2 = day2;
        this.descr = descr;
        this.duration = duration;
        this.day1 = day1;
        this.cap = cap;
        this.pic = pic;
        this.title = title;
        this.location = location;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
