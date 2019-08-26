package com.example.georg.odessaguide.pojo;

public class Places {
    private String place_pic;
    private String place_title;
    public Places() {
    }

    public Places(String place_pic, String place_title, String place_addr, String place_descr) {
        this.place_pic = place_pic;
        this.place_title = place_title;
    }

    public String getPic() {        return place_pic;    }
    public void setPic(String pic) {    this.place_pic = pic;    }
    public String getTitle() {     return place_title;    }
    public void setTitle(String title) {    this.place_title = title;    }
}
