package lesson07.question02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 方式3
 */
public class HomeworkMethod03 {

    public  static Integer result;

    public static void main(String[] args) throws InterruptedException {
        HomeworkMethod03 homeworkMethod03 = new HomeworkMethod03();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                System.out.println("异步计算结果为：" + result);
            }
        });
        new Thread(new Task(cyclicBarrier)).start();
    }

    static class Task  implements Runnable{
        private CyclicBarrier cyc;
        public Task( CyclicBarrier cyc){
            this.cyc = cyc;
        }

        @Override
        public void run() {
            synchronized (this){
                try {
                    result=sum();
                    cyc.await();   // 注意跟CountDownLatch不同，这里在子线程await
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }


}
