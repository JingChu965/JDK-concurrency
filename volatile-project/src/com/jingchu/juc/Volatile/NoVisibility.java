package com.jingchu.juc.Volatile;

/**
 * @description: volatile证明数据的可见性和有序性
 * @author: JingChu
 * @createtime :2020-07-19 16:00:21
 **/
public class NoVisibility {
    private static  boolean read;
    private static int num;
    private static class ReadThread extends Thread{
        @Override
        public void run(){
            while (!read){
                System.out.println(num);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        Thread.sleep(1000);
        num = 42;
        read=true;
        Thread.sleep(2000);
    }
}
