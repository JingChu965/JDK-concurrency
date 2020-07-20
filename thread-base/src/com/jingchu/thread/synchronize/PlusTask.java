package com.jingchu.thread.synchronize;

/**
 * @description: 线程安全测试案例
 * @author: JingChu
 * @createtime :2020-07-20 17:52:17
 **/
public class PlusTask implements Runnable {
    private static int i = 0;

    @Override
    public void run() {
        for (int k = 0; k < 10000; k++) {
            synchronized (PlusTask.class) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(i);
    }
}