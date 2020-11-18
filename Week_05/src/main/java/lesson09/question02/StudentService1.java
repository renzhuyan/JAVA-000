package lesson09.question02;

public class StudentService1 {

    public void initStudent(){
        Student student = Student.builder().id(2).name("renzhuyan2").build();
        System.out.println("学生的id："+student.getId());
        System.out.println("学生的名字："+student.getName());
    }
}
