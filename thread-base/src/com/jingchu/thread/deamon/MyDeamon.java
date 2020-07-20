package com.jingchu.thread.deamon;

/**
 * @description: 守护线程
 * @author: JingChu
 * @createtime :2020-07-20 13:31:16
 **/
public class MyDeamon {
    public static class Deamon extends Thread{
        @Override
        public void run() {
            System.out.println("我是一个活跃的线程");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Deamon();
        t.setDaemon(true);
        t.start();
        Thread.sleep(2000);
    }
}
