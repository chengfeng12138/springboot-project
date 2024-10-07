package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * Join class
 * 使当前线程陷入等待, 等join的线程执行完成后再继续当前线程
 * @author chengfeng
 * @date 2022/1/3 /0003 20:08
 */
public class Join {
    static class ThreadA implements Runnable {

        @Override
        public void run() {
            System.out.println("我是子线程，我先睡⼀秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是子线程，我睡完了⼀秒");
        }
    }

    public static void main(String[] args) throws Exception{
        System.out.println("主线程开始执行..");
        Thread thread = new Thread(new ThreadA());
        thread.start();
        thread.join();
        System.out.println("如果不加join⽅法，我会先被打出来，加了就不⼀样了");
    }
}
