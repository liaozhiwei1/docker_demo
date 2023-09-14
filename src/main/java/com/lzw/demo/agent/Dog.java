package com.lzw.demo.agent;

public class Dog {
    public String say() throws InterruptedException {
        Thread.sleep(1000);
        return "wang";
    }
}
