package test1;

/*
    线程创建和终止 匿名内部类
 */
public class Demo3 {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        }).start();
    }
}
