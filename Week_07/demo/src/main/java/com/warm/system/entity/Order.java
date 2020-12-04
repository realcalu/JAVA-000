package com.warm.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dgd123
 * @since 2018-02-10
 */
@Data
@TableName("t_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("sellerid")
    private int sellerid;

    @TableField("buyerid")
    private int buyerid;

    @TableField("snapshotid")
    private Date snapshotid;

    @TableField("state")
    private int state;

    @TableField("createtime")
    private long createtime;


    @TableField("updatetime")
    private long updatetime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
