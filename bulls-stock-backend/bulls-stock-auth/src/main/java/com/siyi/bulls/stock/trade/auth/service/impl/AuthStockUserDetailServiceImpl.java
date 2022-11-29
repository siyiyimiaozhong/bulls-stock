package com.siyi.bulls.stock.trade.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.siyi.bulls.stock.common.utils.GlobalConstants;
import com.siyi.bulls.stock.trade.auth.mapper.TradeUserMapper;
import com.siyi.bulls.stock.trade.auth.pojo.bo.AuthTradeUser;
import com.siyi.bulls.stock.trade.auth.pojo.entity.TradeUser;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.bulls.stock.trade.auth.service.impl
 * @ClassName: AuthStockUserDetailServiceImpl.java
 * @CreateTime: 2022-11-14  13:03
 * @Version: 1.0
 */
@Service("authStockUserDetailService")
public class AuthStockUserDetailServiceImpl implements UserDetailsService {

    private final TradeUserMapper tradeUserMapper;
    private final CacheManager cacheManager;

    public AuthStockUserDetailServiceImpl(TradeUserMapper tradeUserMapper, CacheManager cacheManager) {
        this.tradeUserMapper = tradeUserMapper;
        this.cacheManager = cacheManager;
    }

    @Override
    public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
        // 1. 查询缓存
        Cache cache = cacheManager.getCache(GlobalConstants.OAUTH_KEY_STOCK_USER_DETAILS);
        if (null != cache && null != cache.get(userNo)) {
            return (UserDetails) cache.get(userNo).get();
        }

        // 2. 缓存未找到, 查询数据库
        QueryWrapper<TradeUser> wrapper = new QueryWrapper<>();
        wrapper.eq("userNo", userNo);
        TradeUser tradeUser = tradeUserMapper.selectOne(wrapper);
        if (null == tradeUser) {
            throw new UsernameNotFoundException(userNo + " not valid!");
        }
        // 3. 对用户信息做封装处理
        UserDetails userDetails = new AuthTradeUser(tradeUser);
        // 4. 将封装的信息放入到缓存内
        cache.put(userNo, userDetails);
        return userDetails;
    }
}
