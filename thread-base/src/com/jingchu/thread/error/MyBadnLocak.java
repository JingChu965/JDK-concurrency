package com.jingchu.thread.error;

/**
 * @description: 错误加锁问题
 * @author: JingChu
 * @createtime :2020-07-20 18:26:23
 **/
public class MyBadnLocak {
    public static class BadnLocak implements Runnable {
        public static Integer i = 0;
        static BadnLocak instance = new BadnLocak();

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {

                synchronized (i){
                    i++;
                }
            }
        }
        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(instance);
            Thread t2 = new Thread(instance);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println(i);
        }
    }


}
