package com.example.shardingsphere.demo.entity;

import lombok.Data;

/**
 * @version 1.0
 * @Description TODO
 * @Author honghonghui
 * @Date
 **/
@Data
public class Order implements Comparable<Order> {
    private Long order_id;
    private Long user_id;

    @Override
    public int compareTo(Order o) {
        return o.getOrder_id().compareTo(this.order_id);
    }
}
