package com.example.chatapp.model;

public class Profile {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setImageUrl(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public Profile(String name, int age, String profile_pic) {
        this.name = name;
        this.age = age;
        this.profile_pic = profile_pic;

    }

    public void setActive(boolean active) {
        this.active = active;
    }
    int id;
    String name;
    int age;
    String profile_pic;
    boolean active;

    public boolean isActive() {
        return active;
    }


}
