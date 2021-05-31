package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
	    Counter c = new Counter();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i=0;i<1000;i++){
                    c.increment();
                }
                System.out.println("Count in thread (1) : "+c.count);

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i=0;i<1000;i++){
                    c.increment();
                }
                System.out.println("Count in thread (2) : "+c.count);

            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final count "+c.count);


    }
}

class Counter{

//    Another way to make our function thread safe

//    AtomicInteger count = new AtomicInteger();
//    public void increment(){
//        count.incrementAndGet();
//    }

    int count;
    public synchronized void increment(){
        count++;
    }
}
