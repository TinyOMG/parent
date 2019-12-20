package service;

import entity.Student;
import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class StudentService {

    @Autowired
    StudentMapper sm;

    @RequestMapping("/insert")
    public int insert(Student student){
        student=new Student("tom2",22,"男2");
        return sm.insert(student);
    }

    public Student getInfo() {
        return new Student("张三2",22,"男2");
    }
}
