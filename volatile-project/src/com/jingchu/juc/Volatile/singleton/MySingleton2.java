package com.jingchu.juc.Volatile.singleton;

/**
 * @description: 多线程下的单例模式
 * @author: JingChu
 * @createtime :2020-07-19 17:05:12
 **/
public class MySingleton2 {
    private static MySingleton2 instance = null;

    private MySingleton2() {
        System.out.println(Thread.currentThread().getName() + " 单例模式构造方法");
    }

    //DLC(Double Lock Check 双端检索机制)
    public static MySingleton2 getInstance() {
        if (instance == null) {
            synchronized (MySingleton2.class) {
                if (instance == null) {
                    instance = new MySingleton2();
                }
            }
        }
        return instance;
    }
    //普通加锁机制
    /*public static synchronized MySingleton2 getInstance() {
        if (instance == null) {

            instance = new MySingleton2();

        }
        return instance;
    }*/

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                MySingleton2.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
