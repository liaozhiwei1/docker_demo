package com.lzw.demo;

import com.lzw.demo.agent.Dog;

public class test {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            System.out.println(new Dog().say());
            Thread.sleep(2000);
        }
    }
}
