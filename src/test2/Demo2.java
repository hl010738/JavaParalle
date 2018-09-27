package test2;

/*
    单例模式 懒汉式 有线程安全性问题
 */
public class Demo2 {

    private Demo2(){
    }

    // volatile 防止指令重排序
    private static volatile Demo2 d;

    public static Demo2 getInstance(){
        if (d == null){
            // synchronized 为了防止多线程访问
            synchronized (Demo2.class){
                if (d == null){
                    d = new Demo2();
                }
            }
        }
        return d;
    }
}
