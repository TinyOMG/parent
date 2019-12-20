package cn.china.service;

import entity.Student;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import service.StudentService;

@EnableEurekaClient
@SpringBootApplication()
@MapperScan("mapper")
@ComponentScan("service")

public class ServiceApplication {
//    @Autowired
//    StudentService ss;
//
//    @RequestMapping("/getInfo")
//    public Student getInfo(){
//        return ss.getInfo();
//    }
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
