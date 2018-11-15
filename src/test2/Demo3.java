package test2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    读写锁
 */
public class Demo3 {

    private Map<String, Object> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    private Lock readLock = rwl.readLock();
    private Lock writeLock = rwl.writeLock();

    public Object getValue(String key){
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + " 读操作在执行");
        try {
            Thread.sleep(3000);
            return map.get(key);
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " 读操作执行完毕");
        }
    }

    public void setValue(String key, Object value){
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + " 写操作在执行");
        try {
            try {
                Thread.sleep(3000);
            } catch (Exception e){

            }
            map.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + " 写操作执行完毕");
        }
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3();

        new Thread(() -> d.setValue("a", 10)).start();

        new Thread(() -> d.setValue("b", 20)).start();

        new Thread(() -> d.setValue("c", 30)).start();

        /*

        new Thread(() -> System.out.println(d.getValue("a"))).start();

        new Thread(() -> System.out.println(d.getValue("a"))).start();

        new Thread(() -> d.setValue("a", 20)).start();

        new Thread(() -> System.out.println(d.getValue("a"))).start();

        new Thread(() -> System.out.println(d.getValue("a"))).start();
        */

    }
}
