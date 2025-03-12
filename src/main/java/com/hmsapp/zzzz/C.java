package com.hmsapp.zzzz;

public class C
{
  public static void main(String[] args) {

    String str="varunVB".toLowerCase();
    str.chars().distinct().forEach(c->System.out.println((char)c+" = "+str.chars().filter(ch-> ch==c).count()));

  }
}