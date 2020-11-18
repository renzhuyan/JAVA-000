package lesson10.question03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 给前面课程提供的Student/Klass/School 实现自动配置和Starter
 */
@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestApplication {

    @Autowired
    private TestService testService;

    @Autowired
    private Klass class1;

    @Autowired
    private ISchool school;

    @Autowired
    private Student student100;

    @Test
    public void test(){
        System.out.println(11);
        testService.test11();

        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型："+class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类："+(class1 instanceof Klass));

        school.ding();

        student100.init();

    }
}
