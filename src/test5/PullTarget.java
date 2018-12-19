package test5;

/*
    消费者
 */
public class PullTarget implements Runnable {

    private Tmall tmall;

    public PullTarget(Tmall tmall){
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true){
            tmall.pull();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
