package test1;

/*
    线程创建和终止 线程池
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo6 {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        });
        pool.shutdown();
    }
}
