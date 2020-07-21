package com.jingchu.juc.thread.factory;


import java.util.concurrent.*;

/**
 * @description: 自定义线程池的测试
 * @author: JingChu
 * @createtime :2020-07-21 17:30:03
 **/
public class MyThreadFactory {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("**********:" + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("AAAA");
                System.out.println("c创建线程"+thread);
                return thread;
            }
        });
        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }
        Thread.sleep(1000);
    }
}
