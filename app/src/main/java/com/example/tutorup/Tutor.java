package com.example.tutorup;

public class Tutor {
    int id;
    String name;
    String email;
    String pass;
    String course;
    String degree;
    double rating;
    double fee;
    double balance;

    public Tutor(int id, String name, String email, String pass, String course, String degree, double rating, double fee, double balance){
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.course = course;
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
    public String getCourse(){
        return course;
    }
    public String getDegree(){
        return degree;
    }
    public double getRating(){
        return rating;
    }
    public double getFee(){
        return fee;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){ this.balance = balance; }
    public void setRating(double rating){
        this.rating = rating;
    }
}
