package service;

import entity.Student;
import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentMapper {
//    @Autowired
//    StudentMapper sm;
    @Override
    public Student getInfo() {
        return new Student("张三",20,"男");
    }
}
