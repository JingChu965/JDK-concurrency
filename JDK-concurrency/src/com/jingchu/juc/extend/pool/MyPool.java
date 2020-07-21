package com.jingchu.juc.extend.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 扩展线程池测试
 * @author: JingChu
 * @createtime :2020-07-21 17:40:18
 **/
public class MyPool {
    public static class MyTask implements Runnable {

        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行" + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("结束执行" + ((MyTask) r).name);
            }
        };
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("AAAAA");
            executorService.execute(task);
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
