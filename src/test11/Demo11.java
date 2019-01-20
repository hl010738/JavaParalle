package test11;

import java.util.concurrent.locks.StampedLock;

/*
    StampedLock
 */
public class Demo11 {

    private int balance;

    private StampedLock stampedLock = new StampedLock();

    public void conditionReadWrite(int value){

        //首先判断balance的值是否符合更新条件
        long stamp = stampedLock.readLock();
        while (balance > 0){
            long writeStamp = stampedLock.tryConvertToWriteLock(stamp);
            if(writeStamp != 0){ // 成功转换为写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else {
                //没有转换为写锁，首先需要释放读锁，然后再拿到写锁
                stampedLock.unlock(stamp);
                //获取写锁
                stamp = stampedLock.writeLock();
            }
        }
        stampedLock.unlock(stamp);
    }

    public void optimisticRead(){
        long stamp = stampedLock.tryOptimisticRead();
        int c = balance;
        //这里可能会出现写操作，因此要判断
        if(!stampedLock.validate(stamp)){
            //需要重新读取
            long readStamp = stampedLock.readLock();
            c = balance;
            stamp = readStamp;
        }

        stampedLock.unlock(stamp);
    }


    public void read(){
        long stamp = stampedLock.readLock();

        stampedLock.tryOptimisticRead();
        int c = balance;

        // do something

        stampedLock.unlock(stamp);
    }

    public void write(int value){
        long stamp = stampedLock.writeLock();

        balance += value;

        stampedLock.unlock(stamp);
    }

    public static void main(String[] args) {

    }
}
