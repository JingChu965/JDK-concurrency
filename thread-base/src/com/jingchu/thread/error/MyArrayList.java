package com.jingchu.thread.error;

import com.jingchu.thread.communication.MyWait;

import java.util.ArrayList;

/**
 * @description: 并发下的ArrayList案例
 * @author: JingChu
 * @createtime :2020-07-20 18:03:44
 **/
public class MyArrayList {
    static ArrayList<Integer> arrayList = new ArrayList<>(10);
    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                arrayList.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(arrayList.size());
    }
}
