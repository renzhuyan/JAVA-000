package lesson14.question02.config;

import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@Import({ DataSourceConfiguration.class})
@MapperScan(basePackages={"lesson14.question02.mapper","com.baomidou.mybatisplus.mappe"})
public class MybatisConfiguration {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${datasource.readSize}")
    private String dataSourceSize;

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext ac) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy(ac));
        //ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        //sqlSessionFactoryBean.setTypeAliasesPackage("clesson14.question02.entity");
        //sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy(ApplicationContext ac) {
        int size = Integer.parseInt(dataSourceSize);
        System.out.println("size:" + size);
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //多个读数据库时
        DataSource writeDataSource = (DataSource)ac.getBean("writeDataSource");
        List<DataSource> readDataSources = (List<DataSource>)ac.getBean("readDataSources");
        for (Integer i = 0; i < size; i++) {
            //设置数据源的key
            targetDataSources.put(DataSourceType.read.getType() + i.toString(), readDataSources.get(i));
        }
        //设置默认数据源
        proxy.setDefaultTargetDataSource(writeDataSource);
        //设置目标多数据源
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}