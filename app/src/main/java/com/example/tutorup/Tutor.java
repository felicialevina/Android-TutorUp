package com.example.tutorup;

public class Tutor {
    int id;
    String name;
    String email;
    String pass;
    String degree;
    float fee;
    float balance;

    public Tutor(int id, String name, String email, String pass, String degree, float fee, float balance){
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.degree = degree;
        this.fee = fee;
        this.balance = balance;
    }
}
