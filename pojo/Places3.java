package com.example.georg.odessaguide.pojo;

public class Places3 {
    private String title;
    private String pic;
    private String descr;
    private boolean blueflag;

    public Places3(String title, String pic, String descr, boolean blueflag) {
        this.title = title;
        this.pic = pic;
        this.descr = descr;
        this.blueflag = blueflag;
    }

    public Places3() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public boolean isBlueflag() {
        return blueflag;
    }

    public void setBlueflag(boolean blueflag) {
        this.blueflag = blueflag;
    }
}
