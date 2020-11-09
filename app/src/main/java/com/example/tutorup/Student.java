package com.example.tutorup;

public class Student {
    int id;
    String name;
    String email;
    String pass;
    String edu;
    double balance;

    public Student(int id, String name, String email, String pass, String edu, double balance){
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.edu = edu;
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
    public String getEdu(){
        return edu;
    }
    public double getBalance(){
        return balance;
    }
    public void setBalance(float balance){this.balance = balance; }
}
