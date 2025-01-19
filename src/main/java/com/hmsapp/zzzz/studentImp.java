package com.hmsapp.zzzz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class studentImp {
    public static void main(String[] args) {


        List<student> li = new ArrayList<>();
        li.add(new student(1, "John", 3));
        li.add(new student(2, "Tom", 2));
        li.add(new student(3, "Alice", 1));
        li.add(new student(4, "Bob", 4));
        li.add(new student(5, "Charlie", 5));
        Collections.sort(li);
        System.out.println(li);
    }
}