package test9;

import java.util.concurrent.*;

/*
    Executor
 */
public class Demo9 {
    public static void main(String[] args) {

        //创建10个线程来处理大量任务
        //ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        //ExecutorService pool = Executors.newFixedThreadPool(10);
        ExecutorService pool = Executors.newCachedThreadPool();

        
        while(true){
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
