package com.example.georg.odessaguide.pojo;

public class Categories {
    private String pic;
    private String title;

    public Categories() {
    }
    public Categories(String pic, String title) {
        this.pic = pic;
        this.title = title;
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
