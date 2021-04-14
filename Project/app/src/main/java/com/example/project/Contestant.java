package com.example.project;


import org.parceler.Parcel;

@Parcel
public class Contestant {
    String name;
    String score;


    public Contestant() {

    }

    public Contestant(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Contestant{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
