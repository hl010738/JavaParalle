package test5;

/*
    生产者
 */
public class PushTarget implements Runnable {

    private Tmall tmall;

    public PushTarget(Tmall tmall){
        this.tmall = tmall;
    }

    @Override
    public void run() {
        while (true){
            tmall.push();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
