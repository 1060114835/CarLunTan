package com.chen.myapplication.ui.home;

public class UserBean {
    private String account;
    private String password;
    private String name;
    private String age;
    private String location;
    private String interist;
    private String school;
    private String introduction;

    public UserBean(String account, String password, String name, String age, String location, String interist, String school, String introduction) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.age = age;
        this.location = location;
        this.interist = interist;
        this.school = school;
        this.introduction = introduction;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInterist() {
        return interist;
    }

    public void setInterist(String interist) {
        this.interist = interist;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
