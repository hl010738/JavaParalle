package test3;


/*
    通过消费者和生产者模式模拟线程等待唤醒机制
 */
public class Main {
    public static void main(String[] args) {
        Tmall tmall = new Tmall();

        PushTarget pushTarget = new PushTarget(tmall);
        PullTarget pullTarget = new PullTarget(tmall);

        new Thread(pushTarget).start();
        new Thread(pushTarget).start();
        new Thread(pushTarget).start();
        new Thread(pushTarget).start();

        new Thread(pullTarget).start();
        new Thread(pullTarget).start();
        new Thread(pullTarget).start();
        new Thread(pullTarget).start();
    }
}
