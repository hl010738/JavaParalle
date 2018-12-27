package test7;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    并发辅助工具类，CyclicBarrier 例子
 */
public class Demo {

    private Random random = new Random();

    private void meeting(CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        Thread.sleep(random.nextInt(4000));
        System.out.println(Thread.currentThread().getName() + " 到达会场，等待开会");
        barrier.await();

        System.out.println(Thread.currentThread().getName() + " 发言...");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        CyclicBarrier barrier = new CyclicBarrier(10, () -> System.out.println("人员到齐，开始开会..."));

        for (int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    demo.meeting(barrier);
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
