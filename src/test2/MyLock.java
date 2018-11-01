package test2;

import com.sun.tools.javac.Main;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/*
    使用AQS自定义可重入锁
 */

public class MyLock implements Lock {

    private int value;

    public int next(){
        lock();
        try {
            Thread.sleep(300);
            return value++;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }finally {
            unlock();
        }
    }

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {

            // 如果第一个线程进来，可以拿到锁，则返回true
            // 如果第二个线程进来，则拿不到锁，返回false.
            // 如果当前进来的线程和保存的线程一致，则可以拿到锁，但是要更新state值. 这种情况就是重入锁.
            // 如何判断进来的线程是第一个线程还是第二个线程呢？

            int state = getState();

            Thread t = Thread.currentThread();

            if(0 == state){
                // expect值不是指期望更新后的值，而是指我们期望的当前值. 要考虑多线程的难问题
                // 例如：我们认为当前值是0，如果线程发现当前值不是0则返回false，更新失败
                if(compareAndSetState(0, arg)){
                    //将当前线程设置为保护的线程，后续会将比较这个线程
                    setExclusiveOwnerThread(t);
                    return true;
                }
            } else if(Thread.currentThread() == t){ //这步实现重入锁
                setState(state + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {

            // 锁的获取和释放线程是一一对应的，那么调用此方法的线程一定是当前线程
            if (Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }

            int state = getState() - arg;

            boolean result = false;

            // 如果state的值等于0，以为锁肯定可以释放成功
            if(0 == getState()){
                setExclusiveOwnerThread(null);
                result = true;
            }

            setState(state);

            return result;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

    // 测试重入reentran
    public void a(){
        lock();
        System.out.println("a");
        b();
        unlock();
    }

    public void b(){
        lock();
        System.out.println("b");
        unlock();
    }

    public static void main(String[] args) {


        MyLock lock = new MyLock();

        new Thread(() -> {
            lock.a();
        }).start();

    }

}
