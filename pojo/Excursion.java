package com.example.georg.odessaguide.pojo;

import java.util.ArrayList;
import java.util.List;

public class Excursion {
    private String title;
    private String duration;
    private String descr;
    private String pic;
    private ArrayList<String> location;


    public Excursion(String title, String duration, String descr, String pic ,ArrayList<String> location) {
        this.title = title;
        this.duration = duration;
        this.descr = descr;
        this.pic = pic;
        this.location = location;
    }

    public ArrayList<String> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    public String getTitle() {

        return title;
    }

    public Excursion() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
