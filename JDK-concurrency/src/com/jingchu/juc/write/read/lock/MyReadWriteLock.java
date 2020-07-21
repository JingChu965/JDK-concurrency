package com.jingchu.juc.write.read.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁测试
 * @author: JingChu
 * @createtime :2020-07-21 14:53:37
 **/
public class MyReadWriteLock {
    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private int value;

    public Object read(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    public void write(Lock lock, int index) {
        lock.lock();
        try {
            Thread.sleep(1000);
            value = index;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final MyReadWriteLock myReadWriteLock = new MyReadWriteLock();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                myReadWriteLock.read(readLock);
                myReadWriteLock.read(lock);
            }
        };
        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                myReadWriteLock.write(writeLock, (int) (System.currentTimeMillis() % 1000));
                myReadWriteLock.write(lock, (int) (System.currentTimeMillis() % 1000));
            }
        };
        for (int i = 0; i < 9; i++) {
            new Thread(readRunnable).start();
        }

        new Thread(writeRunnable).start();

    }
}
