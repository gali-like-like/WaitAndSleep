package com.poortoys.examples;

public class AppMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello, world");
        SleepDemo threadDemo = new SleepDemo();
        threadDemo.start();
        threadDemo.join();
    }

}
