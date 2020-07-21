package com.jingchu.juc.relock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 公平竞争锁（先来后到）
 * @author: JingChu
 * @createtime :2020-07-21 11:06:05
 **/
public class LockFair implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock(true);


    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            try {

                System.out.println(Thread.currentThread().getName() + "获取到锁");
            }finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockFair lockFair = new LockFair();
        Thread t1 = new Thread(lockFair,"A");
        Thread t2 = new Thread(lockFair,"B");
        t1.start();
        t2.start();
    }
}
