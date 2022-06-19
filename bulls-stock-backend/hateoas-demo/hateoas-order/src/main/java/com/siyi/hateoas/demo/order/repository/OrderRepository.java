package com.siyi.hateoas.demo.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siyi.hateoas.demo.order.entity.OrderEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-19 15:46
 * @Description:
 */
@RepositoryRestResource(path ="/order")
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    /**
     * 根据用户查找获取所有的订单信息
     * @param user
     * @return
     */
    public List<OrderEntity> findByUser(@Param("user") String user);
}
