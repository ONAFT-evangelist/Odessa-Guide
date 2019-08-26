package com.example.georg.odessaguide.pojo;

public class Calls {
    private String name;
    private String type;
    private String call;

    public Calls(String name, String type, String call) {
        this.name = name;
        this.type = type;
        this.call = call;
    }

    public Calls() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCall() {
        return String.valueOf(call);
    }

    public void setCall(String call) {
        this.call = call;
   }
}
