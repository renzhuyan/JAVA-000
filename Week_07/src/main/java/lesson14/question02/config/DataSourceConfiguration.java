package lesson14.question02.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主从数据库配置类
 */
@Configuration
public class DataSourceConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="writeDataSource", destroyMethod = "close", initMethod="init")
    @Primary
    @ConfigurationProperties(prefix = "spring.master")
    public DataSource writeDataSource() {
        LOGGER.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    /**
     * 有多少个从库就要配置多少个
     * @return
     */
    @Bean(name = "readDataSource", destroyMethod = "close", initMethod="init")
    @ConfigurationProperties(prefix = "spring.slave")
    public DataSource readDataSourceOne(){
        LOGGER.info("-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    /**
     * 这里的list是多个从库的情况下为了实现简单负载均衡
     * @return
     * @throws SQLException
     */
    @Bean("readDataSources")
    public List<DataSource> readDataSources() throws SQLException {
        List<DataSource> dataSources=new ArrayList<>();
        dataSources.add(readDataSourceOne());
        return dataSources;
    }
}
