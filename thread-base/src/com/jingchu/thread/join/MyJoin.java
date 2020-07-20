package com.jingchu.thread.join;

/**
 * @description: 线程等待实例
 * @author: JingChu
 * @createtime :2020-07-20 13:14:25
 **/
public class MyJoin {
    public volatile static int i=0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i=0;i<100000;i++) {

            }
        }

        public static void main(String[] args) throws InterruptedException {
            AddThread at = new AddThread();
            at.start();
            at.join();
            System.out.println(i);
        }
    }
}
