package com.example.tutorup;

public class Tutor {
    String name;
    String email;
    String pass;
    String course;
    String degree;
    double rating;
    double fee;
    double balance;

    public Tutor(String name, String email, String pass, String course, String degree, double rating, double fee, double balance){
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.course = course;
        this.degree = degree;
        this.rating = rating;
        this.fee = fee;
        this.balance = balance;
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
    public double getFee(){
        return fee;
    }
    public String getDegree(){
        return degree;
    }
    public double getRating(){
        return rating;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(double balance){ this.balance = balance; }
}
