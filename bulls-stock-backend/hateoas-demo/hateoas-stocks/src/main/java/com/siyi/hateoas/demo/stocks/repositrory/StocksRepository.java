package com.siyi.hateoas.demo.stocks.repositrory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.siyi.hateoas.demo.stocks.entity.StocksEntity;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-19 15:05
 * @Description:
 */
@RepositoryRestResource(path = "/stocks")
public interface StocksRepository  extends JpaRepository<StocksEntity, Long> {

    /**
     * 根据股票名称查找所对应的股票数据
     * @param list
     * @return
     */
    List<StocksEntity> findByNameInOrderById(@Param("list")List<String> list);

    /**
     * 根据名称查询股票信息
     * @param name
     * @return
     */
    public StocksEntity findByName(@Param("name")String name);
}