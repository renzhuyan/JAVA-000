package lesson14.question02.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {

    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }
    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.read.getType());
        System.out.println("==:" + DataSourceType.read.getType());
        LOGGER.info("数据库切换到读库...");
    }
    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.write.getType());
        LOGGER.info("数据库切换到写库...");
    }
    public static String getJdbcType() {
        return local.get();
    }
}
