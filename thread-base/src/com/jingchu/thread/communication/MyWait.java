package com.jingchu.thread.communication;

/**
 * @description: 线程通信实例
 * @author: JingChu
 * @createtime :2020-07-20 12:46:09
 **/
public class MyWait {
    final  static Object object = new Object();
    public static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程1启动:");
                try{
                    System.out.println("线程1进入等待");
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("线程1结束");
            }
        }
    }
    public static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("线程2通知线程1可以运行");
                object.notify();
                System.out.println("线程2结束");
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }
}
