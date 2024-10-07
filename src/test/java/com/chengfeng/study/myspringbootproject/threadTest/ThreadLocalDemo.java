package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * ThreadLocalDemo class
 * ThreadLocal:线程本地存储
 *             为每个线程创建一个副本，每个线程可以访问自己内部的副本变量（类似于每个线程中的私有变量，但私有变量会导致线程不轻量）
 * @author chengfeng
 * @date 2022/1/8 /0008 21:20
 */
public class ThreadLocalDemo {
    static class ThreadA implements Runnable {

        private ThreadLocal<String> threadLocal;

        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadA = " + threadLocal.get());
        }
    }

    static class ThreadB implements Runnable {

        private ThreadLocal<String> threadLocal;

        public ThreadB(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadB = " + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        // 两个线程使⽤的同⼀个ThreadLocal实例，但各自可以存取⾃⼰当前线程的⼀个值
        new Thread(new ThreadA(threadLocal)).start();
        new Thread(new ThreadB(threadLocal)).start();
    }
}
