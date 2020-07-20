package com.jingchu.thread.sleep;

/**
 * @description: 休息是中断产生异常实例
 * @author: JingChu
 * @createtime :2020-07-20 12:30:41
 **/
public class MySleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程被中断了");
                        break;
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        System.out.println("设置中断状态");
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }

                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}

