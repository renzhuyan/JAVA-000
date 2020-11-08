package lesson07.question02;

import java.util.concurrent.Semaphore;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 方式6
 */
public class HomeworkMethod06 {

    private Integer result;

    public Integer getResult() {
         return result;
     }
     public void setResult(Integer result) {
         this.result = result;
     }

    public static void main(String[] args) throws InterruptedException {
        HomeworkMethod06 homeworkMethod05 = new HomeworkMethod06();
        Thread thread = new Thread(new Task(homeworkMethod05));
        thread.start();
        long start = System.currentTimeMillis();
        synchronized (homeworkMethod05) {
            homeworkMethod05.wait();//主线程等待
        }
        // 确保 拿到result 并输出
        System.out.println("异步计算结果为：" + homeworkMethod05.getResult());
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    static class Task  implements Runnable{
        private HomeworkMethod06 homeworkMethod05;
        public Task(HomeworkMethod06 homeworkMethod05){
            this.homeworkMethod05 = homeworkMethod05;
        }

        @Override
        public void run() {
            synchronized (this.homeworkMethod05){
                    homeworkMethod05.setResult(sum());
                    homeworkMethod05.notify();
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
