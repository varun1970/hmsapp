package com.hmsapp.zzzz;

public class student implements Comparable<student> {
    int id;
    String name;
    int rank;

    public student(int id, String name, int rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }

//    @Override
//    public String toString() {
//        return this.id ;
//    }

    @Override
    public int compareTo(student s) {
        return this.id - s.id;
    }
}
