package lesson07.question02;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 方式4
 */
public class HomeworkMethod04 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FutureTask futureTask = new FutureTask((new Callable<Integer>() {
            public Integer call() throws Exception {
                return  sum();
            }
        }));
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println("异步计算结果为：" + futureTask.get());
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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