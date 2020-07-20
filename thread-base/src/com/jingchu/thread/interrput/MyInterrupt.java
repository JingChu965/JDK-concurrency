package com.jingchu.thread.interrput;


/**
 * @description: 线程中断实例
 * @author: JingChu
 * @createtime :2020-07-20 12:18:34
 **/
public class MyInterrupt {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        /*Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(i);
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();*/

        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程已经中断了");
                        break;
                    }
                    System.out.println(i+1);
                    Thread.yield();
                }
            }
        };
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }
}
