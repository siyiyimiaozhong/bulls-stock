package com.siyi.trade.bulls.stock.auth.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author siyiyimiaozhong
 * @since 2022-11-13  23:25
 */
@Getter
@Setter
@TableName("t_trade_user")
public class TradeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_no")
    private String userNo;

    @TableField("name")
    private String name;

    @TableField("user_pwd")
    private String userPwd;

    @TableField("phone")
    private String phone;

    @TableField("company_id")
    private Long companyId;

    @TableField("email")
    private String email;

    @TableField("address")
    private String address;

    @TableField("last_login_ip")
    private String lastLoginIp;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 状态(0:有效， 1：锁定， 2：禁用）
     */
    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;


}
