package lesson14.question02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 2、（必做）读写分离-动态切换数据源版本1.0
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan("lesson14.question02.mapper")
public class SpringBootWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWorkApplication.class);
    }
}
