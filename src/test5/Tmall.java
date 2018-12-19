package test5;

public class Tmall {

    private int count;

    private final int MAX_COUNT = 10;

    public synchronized void push(){
        while(count >= MAX_COUNT){
            try {
                System.out.println(Thread.currentThread().getName() + "库存数量达到上限，生产者停止生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "生产者开始生产: " + count);
        notifyAll(); //生产完之后，唤醒消费者继续消费
    }

    public synchronized void pull(){
        while(count <= 0){
            try {
                System.out.println(Thread.currentThread().getName() + "产品销售完毕，消费者等待产品");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + "消费者开始消费: " + count);
        notifyAll(); //消费完之后，唤醒生产者继续生产
    }
}
