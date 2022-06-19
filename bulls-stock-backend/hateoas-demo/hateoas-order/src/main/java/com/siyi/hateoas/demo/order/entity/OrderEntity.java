package com.siyi.hateoas.demo.order.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-19 15:43
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name="T_ORDER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity extends BaseEntity {

    /**
     * 用户名
     */
    private String user;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 成交数量
     */
    private Integer volume;

    /**
     * 成交价格
     */
    private Double price;
}
