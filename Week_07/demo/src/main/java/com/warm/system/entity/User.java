package com.warm.system.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("userlogin")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private int uid;

    @TableId(value = "username")
    private String username;

    @TableId(value = "password")
    private String password;

    @TableId(value = "email")
    private String email;

    @TableId(value = "phone")
    private String phone;


    @TableField(value = "createtime")
    private long createtime;

    @TableField(value = "updatetime")
    private long updatetime;

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }


}
