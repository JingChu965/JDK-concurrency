package com.jingchu.thread.suspend;

/**
 * @description: 挂起和继续执行测试
 * @author: JingChu
 * @createtime :2020-07-20 13:04:30
 **/
public class Mysuspend {
    public static Object object = new Object();
    static ChangeThread t1 = new ChangeThread("t1");
    static ChangeThread t2 = new ChangeThread("t2");

    public static class ChangeThread extends Thread {
        public ChangeThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程正在执行:" + Thread.currentThread().getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
