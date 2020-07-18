package com.jingchu.juc.jmm;

/**
 * @description: 多个线程同时对一个Long型数据读写 遵循原子性
 * @author: JingChu
 * @createtime :2020-07-17 17:14:06
 **/
public class MultiThreadLong {
    public static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }
    public static class ReadT implements Runnable{

        @Override
        public void run() {
            while (true){
                long temp = MultiThreadLong.t;
                if(temp!=111L&&temp!=-99L&&temp!=333L&&temp!=-444L){
                    System.out.println(temp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }
}

