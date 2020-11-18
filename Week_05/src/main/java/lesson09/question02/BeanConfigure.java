package lesson09.question02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {

    @Bean
    public StudentService1 test(){
        return new StudentService1();
    }

}
