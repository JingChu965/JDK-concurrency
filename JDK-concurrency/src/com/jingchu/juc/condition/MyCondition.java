package com.jingchu.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Condition条件
 * @author: JingChu
 * @createtime :2020-07-21 11:22:38
 **/
public class MyCondition implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static Condition condition = reentrantLock.newCondition();

    @Override
    public void run() {
        reentrantLock.lock();
        try {
            condition.await();
            System.out.println("线程正在运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyCondition myCondition = new MyCondition();
        Thread t1 = new Thread(myCondition);
        t1.start();
        Thread.sleep(2000);
        reentrantLock.lock();

        condition.signal();
        reentrantLock.unlock();


    }
}
