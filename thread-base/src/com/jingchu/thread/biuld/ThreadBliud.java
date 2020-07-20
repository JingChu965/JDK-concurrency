package com.jingchu.thread.biuld;

import java.util.concurrent.*;

/**
 * @description: 多线程基础-新建一个线程
 * @author: JingChu
 * @createtime :2020-07-20 10:31:44
 **/
public class ThreadBliud {
    public static void main(String[] args) throws Exception {
        //方法1:
        MyThread1 thread1 = new MyThread1("A");
        thread1.start();
        //方法2
        Thread thread2 = new Thread(new MyThread2());
        thread2.start();
        //方法3
        MyThread3 thread3 = new MyThread3();
        FutureTask futureTask = new FutureTask(thread3);
        new Thread(futureTask).start();
        //方法4
        //设置核心池大小
        int corePoolSize = 5;
        //设置线程池最大能接受多少线程
        int maximumPoolSize = 10;
        //当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
        long keepActiveTime = 200;
        //设置时间单位，秒
        TimeUnit timeUnit = TimeUnit.SECONDS;
        //设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        //创建ThreadPoolExecutor线程池对象，并初始化该对象的各种参数
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit,workQueue);
        //往线程池中循环提交线程
        for (int i = 0; i < 3; i++) {
            //创建线程类对象
            MyThread4 thread4 = new MyThread4(i);
            //开启线程
            executor.execute(thread4);
            //获取线程池中线程的相应参数
            System.out.println("线程池中线程数目：" +executor.getPoolSize() + "，队列中等待执行的任务数目："+executor.getQueue().size() + "，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        //待线程池以及缓存队列中所有的线程任务完成后关闭线程池。
        executor.shutdown();

    }

}

class MyThread1 extends Thread {
    public MyThread1(String name) {
        super(name);
    }
    @Override
    public synchronized void run() {
        System.out.println("继承thread使用run()方法启动线程：" + Thread.currentThread().getName());
    }
}


class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable的run()方法启动线程：" + Thread.currentThread().getName());
    }
}
class MyThread3 implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println("实现callable的call()方法启动线程："+ Thread.currentThread().getName());
        return null;
    }
}
class MyThread4 implements Runnable{

    private int num;

    public MyThread4(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + num );
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + num + "执行完毕");
    }

}