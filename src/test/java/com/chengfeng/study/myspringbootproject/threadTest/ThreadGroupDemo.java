package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * ThreadGroupDemo class
 *
 * @author chengfeng
 * @date 2022/1/2 /0002 17:52
 */
public class ThreadGroupDemo {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("group1") {
            // 继承ThreadGroup并重新定义以下⽅法
            // 在线程成员抛出unchecked exception, 会执⾏此⽅法
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("当前线程名: " + Thread.currentThread().getName());
                System.out.println(t.getName() + ": " + e.getMessage());


            }
        };

        Thread thread1 = new Thread(threadGroup, () -> {
            // 抛出unchecked异常
            throw new RuntimeException("测试异常");
        });

        System.out.println("是否守护线程: " + threadGroup.isDaemon());
        System.out.println("线程状态 = " + thread1.getState());

        thread1.start();

        System.out.println("线程状态 = " + thread1.getState());

        //复制线程组
        Thread[] threads = new Thread[threadGroup.activeCount()];
        ThreadGroup threadGroup1 = new ThreadGroup("group2");
        threadGroup1.enumerate(threads);
    }
}
