package com.jingchu.juc.count.down;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 倒计时器测试
 * @author: JingChu
 * @createtime :2020-07-21 16:15:40
 **/
public class MyCountDown implements Runnable {
    static final CountDownLatch end = new CountDownLatch(5);
    static final MyCountDown myCountDown = new MyCountDown();

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println("检查子任务");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <5 ; i++) {
            executorService.submit(myCountDown);
        }
        //等待检查
        end.await();
        //执行任务
        executorService.shutdown();
        System.out.println("开始最终任务");
    }
}
