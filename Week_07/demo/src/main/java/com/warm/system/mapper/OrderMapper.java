package com.warm.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.warm.system.entity.Order;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dgd123
 * @since 2018-02-10
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT sellerid from t_order where id =#{orderId}")
    long getSellerIdByOrderId(Integer orderId);
}
