package com.hmsapp.zzzz;

import java.util.Arrays;

public class B extends  Thread{
    @Override
    public void run() {
        try {
            if (Thread.currentThread().getName().equals("add")) {
                for(int i=0; i<=10;i++){
                    System.out.println("num:"+i);
                    Thread.sleep(1000);
                }
            }
            else if (Thread.currentThread().getName().equals("chr"))
            {
                for(int i=69; i<=79;i++){
                    System.out.println("car:   "+(char)i);
                    Thread.sleep(1000);
                }
            }
            else if (Thread.currentThread().getName().equals("num")) {
                for(int i=10; i<=20;i++){
                    System.out.println("NUM:      "+i);
                    Thread.sleep(1000);
                }
            }
        }catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
