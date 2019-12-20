package service;

import entity.Student;
import mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentMapper {

    @Override
    public Student getInfo() {
        return new Student("张三2",22,"男2");
    }
}
