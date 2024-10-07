package com.chengfeng.study.myspringbootproject.threadTest;

import java.util.concurrent.*;

/**
 * RunnableTest class
 *
 * @author chengfeng
 * @date 2022/1/1 /0001 20:27
 */
public class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("线程名：" + Thread.currentThread().getName());
        System.out.println("线程优先级：" + Thread.currentThread().getPriority());
        System.out.println("线程组名：" + Thread.currentThread().getThreadGroup().getName());
    }
}

/**
 *  不同于Runnable接口, Callable接口可获取run()方法执行后的返回值
 *  一般是配合线程池使用
 */
class CallableTest implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 模拟计算需要⼀秒
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();
        CallableTest task = new CallableTest();

        FutureTask<Integer> futureTask = new FutureTask<>(task);
        executor.submit(futureTask);
        System.out.println("futureTask.get() = " + futureTask.get());

//        Future<Integer> result = executor.submit(task);
//        // 注意调⽤get⽅法会阻塞当前线程，直到得到结果。
//        // 所以实际编码中建议使⽤可以设置超时时间的重载get⽅法。
//        System.out.println(result.get());
    }


}
