package com.jingchu.juc.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @description: 信号量测试
 * @author: JingChu
 * @createtime :2020-07-21 14:34:39
 **/
public class MySemaphore implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            //模拟线程逻辑代码耗时
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "线程运行结束");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final MySemaphore mySemaphore = new MySemaphore();
        for (int i = 0; i < 20; i++){
            executorService.submit(mySemaphore);
        }
    }
}
