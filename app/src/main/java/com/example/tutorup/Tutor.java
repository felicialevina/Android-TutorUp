package com.example.tutorup;

public class Tutor {
    int id;
    String name;
    String email;
    String pass;
    String degree;
    float rating;
    float fee;
    float balance;

    public Tutor(int id, String name, String email, String pass, String degree, float rating, float fee, float balance){
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.degree = degree;
        this.rating = rating;
        this.fee = fee;
        this.balance = balance;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getDegree(){
        return degree;
    }
    public float getRating(){
        return rating;
    }
    public float getFee(){
        return fee;
    }
    public float getBalance(){
        return balance;
    }
    public float setBalance(){
        return balance;
    }
    public void setRating(float rating){
        this.rating = rating;
    }
}
