package com.jingchu.juc.cyclic.barrier;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 循环栅栏测试
 * @author: JingChu
 * @createtime :2020-07-21 16:35:33
 **/
public class MyCyclicBarrier {
    public static class ColaMan implements Runnable {
        private String colaMan;
        private final CyclicBarrier cyclicBarrier;

        public ColaMan(String colaMan, CyclicBarrier cyclicBarrier) {
            this.colaMan = colaMan;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有可乐凑齐
                cyclicBarrier.await();
                packing();
                //等待所有可乐装箱完毕
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void packing() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(colaMan + ":装箱完毕");
        }
    }

    public static class Boss implements Runnable {
        Boolean flag;
        int n;

        public Boss(Boolean flag, int n) {
            this.flag = flag;
            this.n = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("老板要求:" + n + "个员工给可乐装箱,任务完成");
            } else {
                System.out.println("老板要求:" + n + "个员工给可乐装箱,员工集合");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int n = 4;
        Thread[] allMan = new Thread[n];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(n, new Boss(flag, n));
        System.out.println("召集员工");
        for (int i = 0; i < n; i++) {
            System.out.println("员工" + i + "报道");
            allMan[i] = new Thread(new ColaMan("员工号" + i, cyclic));
            allMan[i].start();
        }
    }
}
