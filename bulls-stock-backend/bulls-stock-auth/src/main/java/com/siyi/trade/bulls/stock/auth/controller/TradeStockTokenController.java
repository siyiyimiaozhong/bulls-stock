package com.siyi.trade.bulls.stock.auth.controller;

import com.siyi.trade.bulls.stock.common.utils.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.trade.bulls.stock.auth.controller
 * @ClassName: TradeStockTokenController.java
 * @CreateTime: 2022-11-17  12:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("/token")
@Slf4j
public class TradeStockTokenController {

    private static final String STOCK_OAUTH_ACCESS = GlobalConstants.OAUTH_PREFIX_KEY;

    private final RedisTemplate stockRedisTemplate;
    private final TokenStore tokenStore;
    private final CacheManager cacheManager;

    public TradeStockTokenController(RedisTemplate stockRedisTemplate, TokenStore tokenStore, CacheManager cacheManager) {
        this.stockRedisTemplate = stockRedisTemplate;
        this.tokenStore = tokenStore;
        this.cacheManager = cacheManager;
    }

    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView require() {
        return new ModelAndView("ftl/login");
    }

    /**
     * 认证页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/success")
    public String success() {
        log.info("token login success!");
        return "login success";
    }

    /**
     * 退出token
     *
     * @param authHeader Authorization
     */
    @DeleteMapping("/logout")
    public String logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isEmpty(authHeader)) {
            return "退出失败，token 为空";
        }

        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StringUtils.isEmpty(accessToken.getValue())) {
            return "退出失败，token 无效";
        }

        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
        cacheManager.getCache(GlobalConstants.OAUTH_KEY_STOCK_USER_DETAILS).evict(auth2Authentication.getName());
        tokenStore.removeAccessToken(accessToken);
        return "退出成功， token 已清除";
    }

    /**
     * 令牌管理调用
     *
     * @param token token
     * @return
     */
    @DeleteMapping("/{token}")
    public String delToken(@PathVariable("token") String token) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return "token 已清除";
    }
}
