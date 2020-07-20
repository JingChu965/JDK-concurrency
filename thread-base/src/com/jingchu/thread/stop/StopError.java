package com.jingchu.thread.stop;

/**
 * @description: 使用stop方法可能导致数据不一致的问题实例
 * @author: JingChu
 * @createtime :2020-07-20 11:41:25
 **/
public class StopError {
    public static User user = new User();

    public static class ChangThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
            }
        }
    }
    public static class ReadThread extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                   if(user.getId()!=Integer.parseInt(user.getName())){
                       System.out.println(user.toString());
                   }
                }
                yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        while (true){
            Thread thread = new ChangThread();
            thread.start();
            Thread.sleep(150);
            thread.stop();
        }
    }
}

class User {
    int id;

    public User() {
        this.id = -1;
        this.name = "-1";
    }

    String name;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
