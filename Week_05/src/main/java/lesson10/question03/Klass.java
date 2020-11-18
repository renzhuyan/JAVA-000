package lesson10.question03;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Klass { 
    
    List<Student> students = new ArrayList<>();
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
