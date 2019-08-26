package com.example.georg.odessaguide.pojo;

public class Places2 {

        private String place_pic;
        private String place_title;
        private String address;
        private String descr_long;
        public Places2() {
        }

    public Places2(String place_pic, String place_title, String address, String descr_long) {
        this.place_pic = place_pic;
        this.place_title = place_title;
        this.address = address;
        this.descr_long = descr_long;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescr_long() {
        return descr_long;
    }

    public void setDescr_long(String descr_long) {
        this.descr_long = descr_long;
    }

    public String getPic() {        return place_pic;    }
        public void setPic(String pic) {    this.place_pic = pic;    }
        public String getTitle() {     return place_title;    }
        public void setTitle(String title) {    this.place_title = title;    }
    }
