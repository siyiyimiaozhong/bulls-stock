package com.siyi.hateoas.demo.order.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-19 14:59
 * @Description:
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    /**
     *
     */
    @UpdateTimestamp
    private Date updateTime;
}
