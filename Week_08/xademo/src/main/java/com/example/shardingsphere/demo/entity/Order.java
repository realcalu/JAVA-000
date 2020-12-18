package com.example.shardingsphere.demo.entity;

import lombok.Data;

/**
 * @version 1.0
 * @Description TODO
 * @Author honghonghui
 * @Date
 **/
@Data
public class Order {
    private long orderid;
    private long buyerid;
    private long snapshotid;
    private int state;
    private long createtime;
    private long updatetime;
}
