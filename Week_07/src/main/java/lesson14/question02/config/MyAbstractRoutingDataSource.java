package lesson14.question02.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource{

    private final int dataSourceNumber;

    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }
    //返回切换的数据源key
    @Override
    protected Object determineCurrentLookupKey() {
        //获取切换的值
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey.equals(DataSourceType.write.getType())) {
            return DataSourceType.write.getType();
        }
        // 读简单负载均衡
        int number = count.getAndAdd(1);
        Integer targetNum = number % dataSourceNumber;
        String lookupKey = DataSourceType.read.getType() + targetNum.toString();
        return lookupKey;
    }
}
