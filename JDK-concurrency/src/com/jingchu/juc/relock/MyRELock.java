package com.jingchu.juc.relock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 重入锁测试
 * @author: JingChu
 * @createtime :2020-07-21 10:07:39
 **/
public class MyRELock implements Runnable {
    private static int i = 0;
    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        for (int k = 0; k < 10000; k++) {
            reentrantLock.lock();
            try{
                i++;
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new MyRELock());
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(i);
    }
}
