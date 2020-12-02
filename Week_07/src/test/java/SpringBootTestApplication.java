import lesson14.question02.SpringBootWorkApplication;
import lesson14.question02.service.OrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringBootWorkApplication.class)
@RunWith(SpringRunner.class)
public class SpringBootTestApplication {

    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    public void test(){
        orderInfoService.query();
    }
}
