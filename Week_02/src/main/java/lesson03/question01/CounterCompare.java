package lesson03.question01;

import java.util.concurrent.atomic.LongAdder;

/**
 * 测试一个小实验
 * long和longAdder做计数器的效率
 * longCounter共耗时ms：528
 * longAdderCounter共耗时ms：14089
 *
 */
public class CounterCompare {
    public static void main(String[] args) {
        longCounter(1000000000);
        longAdderCounter(1000000000);
    }

    public static void longCounter(long num){
        long startMills = System.currentTimeMillis();
        long counter = 0;
        for (long i = 0 ; i < num ; i++) {
            counter++;
        }

        long endMills = System.currentTimeMillis();
        System.out.println("longCounter共耗时ms：" + (endMills-startMills));
    }

    public static void longAdderCounter(long num){
        long startMills = System.currentTimeMillis();
        LongAdder counter = new LongAdder();;
        for (long i = 0 ; i < num ; i++) {
            counter.increment();
        }

        long endMills = System.currentTimeMillis();
        System.out.println("longAdderCounter共耗时ms：" + (endMills-startMills));
    }
}
