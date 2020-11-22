package com.example.tutorup;

public class Topics {
    int id;
    String course_name;
    String topic_name;
    String book_name;

    public Topics(int id, String course_name, String topic_name, String book_name){
        this.id = id;
        this.course_name = course_name;
        this.topic_name = topic_name;
        this.book_name = book_name;
    }
    public int getId(){
        return id;
    }
    public String getCourseName(){
        return course_name;
    }
    public String getTopicName(){
        return topic_name;
    }
    public String getBookName(){
        return book_name;
    }

}
