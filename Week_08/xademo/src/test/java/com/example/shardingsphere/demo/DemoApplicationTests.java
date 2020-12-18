package com.example.shardingsphere.demo;

import com.example.shardingsphere.demo.entity.Order;
import com.example.shardingsphere.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan("com.example.shardingsphere.demo.mapper")
class DemoApplicationTests {
    @Autowired
    OrderService orderService;


    //	插入
    @Test
    void insert() {
        orderService.insertTwoDatabases();
    }


}
