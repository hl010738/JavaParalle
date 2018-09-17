package test1;

/*
    作为线程任务存在

    线程创建和终止 实现Runnable接口
 */

public class Demo2 implements Runnable{
    @Override
    public void run() {
        System.out.println("线程执行了");
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();

        Thread t = new Thread(d);
        t.start();
    }
}
