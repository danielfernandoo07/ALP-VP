package com.example.vp_alpapp.model;
public class User {
    private int id;
    private String name;

    private String photo;



    // Konstruktor, getter, dan setter

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter dan Setter untuk semua properti

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

