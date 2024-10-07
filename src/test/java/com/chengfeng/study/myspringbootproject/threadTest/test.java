package com.chengfeng.study.myspringbootproject.threadTest;

/**
 * test class
 *
 * @author chengfeng
 * @date 2022/1/1 /0001 20:29
 */
public class test {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableTest());
        thread.start();
        System.out.println("哈哈哈...");
    }
}
