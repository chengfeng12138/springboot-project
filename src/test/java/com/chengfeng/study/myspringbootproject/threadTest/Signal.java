package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * Signal class
 * 信号量
 * 代码示例: 线程A输出0,然后线程B输出1,线程A输出2,以此类推
 * @author chengfeng
 * @date 2022/1/2 /0002 23:06
 */
public class Signal {

    private static volatile int signal = 0;

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 0) {
                    System.out.println("threadA: " + signal);
                    synchronized (this) {
                        signal ++;
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable{

        @Override
        public void run() {
            while (signal < 5) {
                if (signal % 2 == 1) {
                    System.out.println("threadB: " + signal);
                    synchronized (this) {
                        signal ++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}
