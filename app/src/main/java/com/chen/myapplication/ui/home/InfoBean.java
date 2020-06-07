package com.chen.myapplication.ui.home;

public class InfoBean {

    public InfoBean(String name, String date, String introduction) {
        this.name = name;
        this.date = date;
        this.introduction = introduction;
    }

    private String name;
    private String date;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
