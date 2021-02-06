package com.example.chatapp.model;

public class Profile {
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Profile(String fullName, int age, String imageUrl, boolean active) {
        this.fullName = fullName;
        this.age = age;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    String fullName;
    int age;
    String imageUrl;
    boolean active;

    public boolean isActive() {
        return active;
    }


}
