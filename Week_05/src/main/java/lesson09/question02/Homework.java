package lesson09.question02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提
 * 交到Github。
 * 方式一：@Component、@Service、@Bean方式装配bean,AnnotationConfigApplicationContext获取bean使用
 * 方式二：@Component、@Service、@Bean方式装配bean，@Autowired自动注入bean使用（工作中常用）
 * 方式三：用@Configuration定义一个配置类，在配置类中方法@bean方式装配bean，@Autowired自动注入bean使用（工作中常用）
 * 方法四：XML文件方式装配bean
 */
@Component
//@ComponentScan(basePackageClasses = {StudentService.class})
@ComponentScan(basePackages = {"lesson09"})
public class Homework {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentService1 studentService1;

    public static void main(String[] args) {
        // 方式一
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudentService.class);
        StudentService studentService = applicationContext.getBean("studentService",StudentService.class);
        studentService.initStudent();*/

        // 方式二
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Homework.class);
        Homework homework = applicationContext.getBean("homework",Homework.class);
        homework.studentService.initStudent();*/

        // 方式三
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Homework.class);
        Homework homework = applicationContext.getBean("homework",Homework.class);
        homework.studentService1.initStudent();*/

        // 方式四
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudentService2.class);
        StudentService2 studentService2 = applicationContext.getBean("studentService2",StudentService2.class);
        studentService2.initStudent();

    }
}
