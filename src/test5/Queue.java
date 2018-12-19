package test5;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue<E> {

    private Object[] obj;

    private int addIndex;

    private int removeIndex;

    private int size;

    private Lock lock = new ReentrantLock();
    Condition addCondition = lock.newCondition();
    Condition removeCondition = lock.newCondition();

    public Queue(int size){
        this.obj = new Object[size];
    }

    public void add(E e){
        lock.lock();
        while (size == obj.length){
            try {
                System.out.println(Thread.currentThread().getName() + "队列已满，不能添加");
                addCondition.await();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        obj[addIndex] = e;
        if(addIndex++ == 3){
            addIndex = 0;
        }
        size++;
        removeCondition.signal();
        lock.unlock();
    }

    public void remove(){
        lock.lock();
        while (size == 0){
            try {
                System.out.println(Thread.currentThread().getName() + "队列已空，不能移除");
                removeCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        obj[removeIndex] = null;
        if(++removeIndex == obj.length){
            removeIndex = 0;
        }
        size--;
        addCondition.signal();
        lock.unlock();
    }
}
