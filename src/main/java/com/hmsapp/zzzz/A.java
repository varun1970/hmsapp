package com.hmsapp.zzzz;

public class A {
    public static void main(String[] args) {
        B b1=new B();
        B b2=new B();
        B b3=new B();

        b1.setName("add");
        b2.setName("chr");
        b3.setName("num");

        b1.start();
        b2.start();
        b3.start();
    }
}
