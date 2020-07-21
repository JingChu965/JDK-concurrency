package com.jingchu.juc.relock;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 锁限时等待
 * @author: JingChu
 * @createtime :2020-07-21 10:51:14
 **/
public class LockWaitTime implements Runnable{
    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if(reentrantLock.tryLock(3, TimeUnit.SECONDS)){
                Thread.sleep(4000);
            }else {
                System.out.println("获取锁失败");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(reentrantLock.isHeldByCurrentThread()){
                reentrantLock.unlock();
            }
            System.out.println(Thread.currentThread().getId()+"线程运行结束");
        }
    }

    public static void main(String[] args) {
        LockWaitTime lockWaitTime = new LockWaitTime();
        Thread t1 = new Thread(lockWaitTime);
        Thread t2 = new Thread(lockWaitTime);
        t1.start();
        t2.start();
    }
}
