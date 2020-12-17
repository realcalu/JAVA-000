package com.example.shardingsphere.demo.mapper;

import com.example.shardingsphere.demo.entity.Order;
import org.apache.ibatis.annotations.Param;
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
    int intsert(@Param("orderid") long orderid, @Param("userid") long userid);
    int deleteAll();
    List<Order> queryAll();


}