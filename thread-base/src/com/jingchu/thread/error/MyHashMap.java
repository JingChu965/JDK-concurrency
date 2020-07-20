package com.jingchu.thread.error;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 并发下的HashMap测试案例
 * @author: JingChu
 * @createtime :2020-07-20 18:12:50
 **/
public class MyHashMap {
    static Map<String, String> map = new HashMap<>();

    public static class AddThread implements Runnable {
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 10000; i = i + 2) {
                map.put(Integer.toString(i), Integer.toBinaryString(i));

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
