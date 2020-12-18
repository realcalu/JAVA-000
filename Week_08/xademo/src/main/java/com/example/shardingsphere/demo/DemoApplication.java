package com.example.shardingsphere.demo;

import com.example.shardingsphere.demo.entity.Order;
import com.example.shardingsphere.demo.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("com.example.shardingsphere.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        OrderService orderService = new OrderService();
        List<Order> query = orderService.query();
        System.out.println(query);
    }

}
