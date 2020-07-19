package com.jingchu.juc.Volatile;

/**
 * @description: 证明volatile不保证原子性
 * @author: JingChu
 * @createtime :2020-07-19 16:27:01
 **/
public class PlusTask implements Runnable {
    private volatile static int i = 0;

    @Override
    public void run() {
        for (int k = 0; k < 10000; k++) {
            i++;
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