package com.jingchu.juc.relock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 死锁的锁中断解决
 * @author: JingChu
 * @createtime :2020-07-21 10:28:25
 **/
public class LockInterrupt implements Runnable {
    public static ReentrantLock reentrantLock1 = new ReentrantLock();
    public static ReentrantLock reentrantLock2 = new ReentrantLock();
    int lock;

    /**
     * 控制加锁顺序，方便构成死锁代码
     *
     * @param lock
     */
    public LockInterrupt(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                reentrantLock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock2.lockInterruptibly();
            }else {
                reentrantLock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock1.isHeldByCurrentThread()) {
                reentrantLock1.unlock();
            }
            if (reentrantLock2.isHeldByCurrentThread()) {
                reentrantLock2.unlock();
            }
            System.out.println(Thread.currentThread().getId()+"退出线程");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockInterrupt lockInterrupt1 = new LockInterrupt(1);
        LockInterrupt lockInterrupt2 = new LockInterrupt(2);
        Thread t1 = new Thread(lockInterrupt1);
        Thread t2 = new Thread(lockInterrupt2);
        t1.start();t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
