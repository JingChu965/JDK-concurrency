package com.jingchu.thread.group;

/**
 * @description: 线程组测试实例
 * @author: JingChu
 * @createtime :2020-07-20 13:26:18
 **/
public class MyThreadGroup implements Runnable {


    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName());
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("线程组");
        Thread t1 = new Thread(threadGroup,new MyThreadGroup(),"T1");
        Thread t2 = new Thread(threadGroup,new MyThreadGroup(),"T2");
        t1.start();
        t2.start();
        System.out.println(threadGroup.activeCount());
        threadGroup.list();
    }
}
