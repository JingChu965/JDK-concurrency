package com.jingchu.juc.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @description: volatile关键字证明可见性
 * @author: JingChu
 * @createtime :2020-07-17 18:33:00
 **/
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 运行 ");
            //暂停一会线程
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t 更新num变量 "+myData.num);
        },"AAA").start();
        //第二个线程是主线程
        while (myData.num==0){
            //main线程执行死循环,如果读取不到变化则一直死循环
        }
        //main线程读取到num的变化则会执行这条语句
        System.out.println(Thread.currentThread().getName()+"\t 运行 num变量的值为："+myData.num);
    }
}

/**
 * 验证volatile的可见性
 * 1.没有加volatile时，没有可见性
 */
class MyData {
    volatile int num = 0;

    public void addTo60() {
        this.num = 60;
    }
}
