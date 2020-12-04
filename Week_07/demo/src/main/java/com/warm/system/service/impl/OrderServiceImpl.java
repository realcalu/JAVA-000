package com.warm.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.warm.common.DBTypeEnum;
import com.warm.common.DataSourceSwitch;
import com.warm.system.entity.Order;
import com.warm.system.mapper.OrderMapper;
import com.warm.system.service.db2.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dgd123
 * @since 2018-02-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @DataSourceSwitch(DBTypeEnum.db1)
    public List<Order> getOrderList() {
        return selectList(null);
    }

    @Override
    @DataSourceSwitch(DBTypeEnum.db1)
    public long getSellerIdByOrderId(Integer orderId) {
        return orderMapper.getSellerIdByOrderId(orderId);
    }
}
