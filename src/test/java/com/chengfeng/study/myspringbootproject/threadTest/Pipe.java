package com.chengfeng.study.myspringbootproject.threadTest;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Pipe class
 * 管道通信(当需要一个线程给另一个线程发送信息比如字符串或者文件时) 基于字符流
 * 代码执行流程:
 *  1. 线程ReaderThread开始执⾏，
 *  2. 线程ReaderThread使⽤管道reader.read()进⼊”阻塞“，
 *  3. 线程WriterThread开始执⾏，
 *  4. 线程WriterThread⽤writer.write("test")往管道写⼊字符串，
 *  5. 线程WriterThread使⽤writer.close()结束管道写⼊，并执⾏完毕，
 *  6. 线程ReaderThread接受到管道输出的字符串并打印，
 *  7. 线程ReaderThread执⾏完毕。
 *
 * @author chengfeng
 * @date 2022/1/3 /0003 19:39
 */
public class Pipe {
    static class ReaderThread implements Runnable {

        private PipedReader reader;

        public ReaderThread (PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            System.out.println("this is reader");
            int receive = 0;
            try {
                while ((receive = reader.read()) != -1)  {
                    System.out.print((char)receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class WriterThread implements Runnable {

        private PipedWriter pipedWriter;

        public WriterThread (PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            System.out.println("this is writer");
            int receive = 0;
            try {
                pipedWriter.write("test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pipedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        writer.connect(reader);

        new Thread(new ReaderThread(reader)).start();
        Thread.sleep(1000);
        new Thread(new WriterThread(writer)).start();
    }
}
