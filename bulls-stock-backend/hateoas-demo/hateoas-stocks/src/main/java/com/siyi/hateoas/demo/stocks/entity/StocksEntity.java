package com.siyi.hateoas.demo.stocks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
