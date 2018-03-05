package com.company;

public class Main {

    public static void main(String[] args) {

        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");

        t1.start();

        //starting second thread only after 2-sec waiting first thread or when it dies/completes job
        try {
            t1.join(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t2.start();

        //starting third thread only after first thread completes job
        try {
            t1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t3.start();

        // gain opportunity to all threads completes job before main thread completes job
        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("All thread's comletes there jobs, finishing program");
    }
}


class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread is working:::" + Thread.currentThread().getName());

        try {
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Thread finished work::"+Thread.currentThread().getName());
    }
}
