package com.siyi.trade.bulls.stock.auth.pojo.bo;

import com.siyi.trade.bulls.stock.auth.pojo.entity.TradeUser;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.bulls.stock.trade.auth.pojo.bo
 * @ClassName: AuthTradeUser.java
 * @CreateTime: 2022-11-14  07:07
 * @Version: 1.0
 */
public class AuthTradeUser extends User {
    private static final long serialVersionUUID = -1L;

    /**
     * 业务用户信息
     */
    private TradeUser tradeUser;

    public AuthTradeUser(TradeUser tradeUser) {
        // OAUTH2认证用户信息构造处理
        super(tradeUser.getUserNo(), tradeUser.getUserPwd(), tradeUser.getStatus() == 0,
                true, true, tradeUser.getStatus() == 0, Collections.emptyList());
        this.tradeUser = tradeUser;
    }

    public TradeUser getTradeUser() {
        return tradeUser;
    }
}
