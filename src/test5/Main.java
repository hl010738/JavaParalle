package test5;


/*
    使用有限队列实现队列缓存
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
