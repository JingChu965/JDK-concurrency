package com.jingchu.juc.jmm;


/**
 * @description: 有序性证明
 * @author: JingChu
 * @createtime :2020-07-18 09:51:25
 **/
public class OrderExample {
    public static void main(String[] args) {
        MyOrderData myData = new MyOrderData();
        new Thread(()->{
            try {
                myData.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            myData.read();
        },"BBB").start();
    }
}
class MyOrderData{
    int a = 0;
    boolean flag = false;

    public void write() throws InterruptedException {
        flag = true;
        Thread.sleep(2000);
        a = 1;
        System.out.println(Thread.currentThread().getName()+" "+a);
    }

    public void read() {
        if (flag) {

            System.out.println(Thread.currentThread().getName()+" "+a);
        }
    }
}
