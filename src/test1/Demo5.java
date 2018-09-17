package test1;

/*
    线程创建和终止 定时器
 */

import java.util.Timer;
import java.util.TimerTask;

public class Demo5 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("线程执行");
            }
        }, 0, 1000);
    }
}
