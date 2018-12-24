package test6;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    使用多线程 模拟数据库连接池
 */
public class DBConnentionPool {

    private LinkedList<String> pool = new LinkedList<>();

    private static final int MAX_CONNECTION = 10;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public DBConnentionPool(){
        for (int i = 0; i < MAX_CONNECTION; i++){
            String conn = "Connection - " + i;
            pool.add(conn);
        }
    }

    public String getConnection() throws InterruptedException {
        String result = null;
        lock.lock();
        try {
            while (pool.size() == 0){
                condition.await();
            }
            if(!pool.isEmpty()){
                result = pool.removeFirst();
            }

            return result;
        } finally {
            lock.unlock();
        }
    }

    public void release(String conn){
        if(null != conn){
            lock.lock();
            try {
                pool.addLast(conn);
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
