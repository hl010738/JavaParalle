package test2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/*
    自定义可重入锁
 */

public class MyLock implements Lock {

    private boolean isLock = false;

    private Thread lockBy = null;

    private int count = 0;

    @Override
    public synchronized void lock() {

        Thread currentThread = Thread.currentThread();

        while (isLock && currentThread != lockBy){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLock = true;
        lockBy = currentThread;
        count++;
    }

    @Override
    public synchronized void unlock() {
        if (lockBy == Thread.currentThread()){
            count--;
            if (count == 0){
                isLock = false;
                notify();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
