package com.example.shardingsphere.demo;

import com.example.shardingsphere.demo.entity.Order;
import com.example.shardingsphere.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.example.shardingsphere.demo.mapper")
class DemoApplicationTests {
	@Autowired
	OrderService orderService;

//	查询
	@Test
	void query() {
		List<Order> query = orderService.query();
		query.stream().sorted().forEach(p->System.out.println(p.toString()));
	}

//	插入
	@Test
	void insert() {
		for (int i = 1; i < 22; i++) {
			int insert = orderService.insert(i, i%20);
		}
	}
//	删除
	@Test
	void deleteAll(){
		int i = orderService.deleteAll();
	}

}
