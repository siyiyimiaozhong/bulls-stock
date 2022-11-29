package com.siyi.trade.bulls.stock.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.bulls.stock.entity.base
 * @ClassName: BaseEntity.java
 * @CreateTime: 2022-11-13  21:18
 * @Version: 1.0
 */
@Setter
@Getter
public class BaseEntity implements Serializable {

    /**
     * 唯一主键标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
}
