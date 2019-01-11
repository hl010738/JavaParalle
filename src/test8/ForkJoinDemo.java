package test8;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/*
    Fork Join 框架
    从1+2+3+4+.....+100
 */
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private int begin;
    private int end;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LocalDateTime t1 = LocalDateTime.now();
        ForkJoinPool pool = new ForkJoinPool(4);
        ForkJoinTask<Integer> result = pool.submit(new ForkJoinDemo(1, 1000000000));
        System.out.println("计算结果为：" + result.get());
        LocalDateTime t2 = LocalDateTime.now();
        System.out.println("并发执行时间: " + t2.until(t1, ChronoUnit.SECONDS)); // 18秒

        t1 = LocalDateTime.now();
        int sum = 0;
        for (int i = 1; i <= 1000000000; i++){
            sum += i;
        }
        System.out.println("计算结果为：" + sum);
        t2 = LocalDateTime.now();
        System.out.println("顺序执行时间: " + t2.until(t1, ChronoUnit.SECONDS)); // 0秒
    }

    public ForkJoinDemo(int begin, int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //任务拆分
        if(end - begin <= 2){
            //计算
            for (int i = begin; i <= end; i++){
                sum += i;
            }
        } else {
            //拆分
            ForkJoinDemo d1 = new ForkJoinDemo(begin, (begin + end) / 2);
            ForkJoinDemo d2 = new ForkJoinDemo((begin + end) / 2 + 1, end);

            //执行任务
            d1.fork();
            d2.fork();

            int a = d1.join();
            int b = d2.join();

            sum = a + b;
        }
        return sum;
    }
}
