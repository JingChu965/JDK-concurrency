package com.jingchu.thread.priority;

/**
 * @description: 线程的优先级测试
 * @author: JingChu
 * @createtime :2020-07-20 13:38:24
 **/
public class MyPriority {
    public static class HPriority extends Thread{
        static int count=0;

        @Override
        public void run() {
            while (true){
                synchronized (MyPriority.class){
                    count++;
                    if(count>1000000){
                        System.out.println("高优先级线程结束");
                        break;
                    }
                }
            }
        }
    }
    public static class Lpriority extends Thread{
        static int count=0;

        @Override
        public void run() {
            while (true){
                synchronized (MyPriority.class){
                    count++;
                    if(count>1000000){
                        System.out.println("低优先级线程结束");
                        break;
                    }
                }
            }
        }
    }
    public static class Mpriority extends Thread{
        static int count=0;

        @Override
        public void run() {
            while (true){
                synchronized (MyPriority.class){
                    count++;
                    if(count>1000000){
                        System.out.println("中优先级线程结束");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HPriority();
        Thread low = new Lpriority();
        Thread mium = new Mpriority();
        high.setPriority(9);
        low.setPriority(3);
        mium.setPriority(6);
        high.start();
        low.start();
        mium.start();
    }
}
