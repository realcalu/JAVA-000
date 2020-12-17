package com.example.shardingsphere.demo.service;

import com.example.shardingsphere.demo.entity.Order;
import com.example.shardingsphere.demo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @Description TODO
 * @Author honghonghui
 * @Date
 **/

@Service
public class OrderService {
    @Autowired
    OrderMapper mapper;
    public List<Order> query(){
        return mapper.queryAll();
    }
    public int insert(long orderid,long userid){
        return mapper.intsert(orderid,userid);
    }

    public int deleteAll(){
        return mapper.deleteAll();
    }

}
