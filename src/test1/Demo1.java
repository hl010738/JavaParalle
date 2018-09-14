package test1;

/*
    线程创建和终止 继承Thread
 */

public class Demo1 extends Thread {

    public Demo1(String name){
        super(name);
    }

    @Override
    public void run() {
        while (!interrupted()){
            System.out.println(this.getName() + " 线程执行");
        }
    }

    public static void main(String[] args) {
        Demo1 d1 = new Demo1("Test thread 1");
        Demo1 d2 = new Demo1("Test thread 2");

        //d1.setDaemon(true);

        d1.start();
        d2.start();
    }
}

