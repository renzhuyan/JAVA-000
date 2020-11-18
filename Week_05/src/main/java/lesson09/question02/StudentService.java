package lesson09.question02;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void initStudent(){
        Student student = Student.builder().id(1).name("renzhuyan").build();
        System.out.println("学生的id："+student.getId());
        System.out.println("学生的名字："+student.getName());
    }
}
