package com.example.georg.odessaguide.pojo;

public class Religion {
    private String cexcl;
    private String cincl;
    private String cost;
    private String descr;
    private String duration;
    private String pic;
    private String title;

    public Religion() {
    }

    public Religion(String cexcl, String cincl, String cost, String descr, String duration, String pic, String title) {
        this.cexcl = cexcl;
        this.cincl = cincl;
        this.cost = cost;
        this.descr = descr;
        this.duration = duration;
        this.pic = pic;
        this.title = title;
    }

    public String getCexcl() {
        return cexcl;
    }

    public void setCexcl(String cexcl) {
        this.cexcl = cexcl;
    }

    public String getCincl() {
        return cincl;
    }

    public void setCincl(String cincl) {
        this.cincl = cincl;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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
