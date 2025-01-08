package com.hmsapp.zzzz;


import java.util.*;

public class iterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        Iterator l=  list.iterator();
        while(l.hasNext()){
            System.out.println(l.next());
        }
        for(String s:list){
            System.out.println(s);
        }

        ListIterator li = list.listIterator();
        while(li.hasNext()){ 
            System.out.println(li.next());
        }
        while(li.hasPrevious()){
            System.out.println(li.previous());
        }
        list.forEach(x-> System.out.println(x));
    }
}
