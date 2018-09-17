package test1;

/*
    线程创建和终止 拥有返回值和抛出异常
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo4 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("线程执行");
        Thread.sleep(3000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo4 d = new Demo4();
        FutureTask<Integer> task = new FutureTask<>(d);
        Thread t = new Thread(task);
        t.start();
        Integer result = task.get();

        System.out.println("执行结果是：" + result);
    }
}
