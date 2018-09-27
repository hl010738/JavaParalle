package test2;


/*
    单例模式 饿汉式 没有现成安全性问题
 */

public class Demo1 {

    private static Demo1 d = new Demo1();

    private Demo1(){

    }

    public static Demo1 getInstance(){
        return d;
    }
}

