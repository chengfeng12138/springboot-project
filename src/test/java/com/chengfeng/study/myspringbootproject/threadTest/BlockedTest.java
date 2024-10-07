package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * BlockedTest class
 *
 * @author chengfeng
 * @date 2022/1/2 /0002 22:09
 */
public class BlockedTest {
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();

        try {
            a.join(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            Thread.sleep(1000L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        b.start();

        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }

    private static synchronized void testMethod() {
        try {
            System.out.println("当前执行线程: " + Thread.currentThread().getName());
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
