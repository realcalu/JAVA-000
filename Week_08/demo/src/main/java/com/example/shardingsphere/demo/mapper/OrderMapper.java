package com.example.shardingsphere.demo.mapper;

import com.example.shardingsphere.demo.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author honghonghui
 * @Date
 **/
@Repository
public interface OrderMapper {
    int intsert(Order order);
    int deleteAll();
    List<Order> queryAll();


}