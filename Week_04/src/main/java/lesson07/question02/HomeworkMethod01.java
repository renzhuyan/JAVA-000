package lesson07.question02;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 方式1
 */
public class HomeworkMethod01 {

    private Integer result;

    public Integer getResult() {
         return result;
     }
     public void setResult(Integer result) {
         this.result = result;
     }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        long start = System.currentTimeMillis();
        HomeworkMethod01 homeworkMethod01 = new HomeworkMethod01();
        // 在这里创建一个线程或线程池
        new Thread(new Task(homeworkMethod01,countDownLatch)).start();
        // 确保 拿到result 并输出
        countDownLatch.await();
        System.out.println("异步计算结果为：" + homeworkMethod01.getResult());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    static class Task  implements Runnable{
        private HomeworkMethod01 homeworkMethod01;
        private CountDownLatch latch;
        public Task(HomeworkMethod01 homeworkMethod01,CountDownLatch latch){
            this.homeworkMethod01 = homeworkMethod01;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this){
                homeworkMethod01.setResult(sum());
                latch.countDown();
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
