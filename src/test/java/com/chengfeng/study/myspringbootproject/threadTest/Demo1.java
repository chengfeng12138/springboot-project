package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * Demo1 class
 *
 * @author chengfeng
 * @date 2022/1/3 /0003 20:02
 */
public class Demo1 {
    static class threadA implements Runnable {

        @Override
        public void run() {
            System.out.println("this is thread A");
        }
    }

    static class threadB implements Runnable {

        @Override
        public void run() {
            System.out.println("this is thread B");
        }
    }

    public static void main(String[] args) throws Exception{
        new Thread(new threadA()).start();
        Thread.sleep(1000);

        new Thread(new threadB()).start();
    }
}
