package com.chengfeng.study.myspringbootproject.threadTest.completableFutureDemo;

import java.util.concurrent.*;

/**
 * Demo1 class
 * 用两个线程 T1 和 T2 来完成烧水泡茶程序
 * T1 负责洗水壶、烧开水、泡茶这三道工序
 * T2 负责洗茶壶、洗茶杯、拿茶叶三道工序
 * 其中 T1 在执行泡茶这道工序时需要等待 T2 完成拿茶叶的工序
 * @author chengfeng
 * @date 2022/9/18 /0018 0:09
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture
                .runAsync(() -> {
                    try {
                        System.out.println("T1:洗水壶...");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("T1:烧开水...");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, executorService);
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        System.out.println("T2:洗茶壶...");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("T2:洗茶杯...");
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("T2:拿茶叶...");
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "龙井";
                }, executorService);
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(f3.join());

        executorService.shutdown();
    }
}
