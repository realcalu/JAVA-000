package com.example.shardingsphere.demo.service;

import com.example.shardingsphere.demo.entity.Order;
import com.example.shardingsphere.demo.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    public List<Order> query() {
        return mapper.queryAll();
    }

    public int insert(Order order) {
        return mapper.intsert(order);
    }

    public int deleteAll() {
        return mapper.deleteAll();
    }

    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public int insertTwoDatabases() {
        Order order = new Order();
        order.setOrderid(1);
        order.setBuyerid(1);
        order.setSnapshotid(102);
        order.setCreatetime(new Date().getTime());
        order.setUpdatetime(new Date().getTime());
        order.setState(1);
        mapper.intsert(order);


        order.setOrderid(100);
        order.setBuyerid(2);
        order.setSnapshotid(102);
        order.setCreatetime(new Date().getTime());
        order.setUpdatetime(new Date().getTime());
        order.setState(1);
        mapper.intsert(order);
        return 0;
    }

}
