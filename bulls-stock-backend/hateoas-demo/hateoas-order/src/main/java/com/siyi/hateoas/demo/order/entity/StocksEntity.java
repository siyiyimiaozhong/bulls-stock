package com.siyi.hateoas.demo.order.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-19 14:59
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "T_STOCKS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StocksEntity extends BaseEntity {

    /**
     * 股票名称
     */
    private String name;

    /**
     * 股票价格
     */
    private Double price;

}
