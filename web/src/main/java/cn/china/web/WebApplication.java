package cn.china.web;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@EnableEurekaClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController 
@Configuration
public class WebApplication {

    @Autowired
    RestTemplate rest;

    @Autowired
    LoadBalancerClient loadBalancerClient;


    @Bean
    @LoadBalanced
   public RestTemplate getRest(){
       return new RestTemplate();
   }

    @Bean //负载均衡随机规则,随机调用集群服务
    public IRule ribbonRule(){
        return new RandomRule();
    }

   @RequestMapping(value="/getinfo",produces = "application/json;charset=UTF-8")
   public Student myGet(HttpServletResponse res){
       res.setHeader("Access-Control-Allow-Origin", "*");
       ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
       String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/getInfo";
       Student stu=rest.getForObject("http://provider/getInfo",Student.class);
     return stu;
   }


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
