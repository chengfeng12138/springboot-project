package com.chengfeng.study.myspringbootproject.extendTest;

/**
 * Son class
 *
 * @author chengfeng
 * @date 2022/1/1 /0001 20:12
 */
public class Son extends Parent{

    public void test() {
        //重写父类的方法
        System.out.println("i am son");
//        super.test();
    }

    public static void main(String[] args) {
        Parent son = new Son();
        System.out.println("son = " + son);
        son.test();

        Parent son1 = new Parent();
        son1.test();
    }
}
