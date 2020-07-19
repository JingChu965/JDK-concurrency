package com.jingchu.juc.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 不使用锁解决原子性问题
 * @author: JingChu
 * @createtime :2020-07-19 16:31:48
 **/
public class AtomicPlusTask implements Runnable{
    private  static AtomicInteger  atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        for (int k = 0; k < 10000; k++) {
            atomicInteger.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new AtomicPlusTask());
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(atomicInteger);
    }
}

