package com.siyi.trade.bulls.stock.common.utils;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.trade.bulls.stock.common.utils
 * @ClassName: GlobalConstants.java
 * @CreateTime: 2022-11-13  16:31
 * @Version: 1.0
 */
public class GlobalConstants {


    /**
     * 缓存 -- 用户信息前缀
     */
    public static final String OAUTH_KEY_STOCK_USER_DETAILS = "oauth:stock:user_details";
    /**
     * 缓存 --- 客户端信息前缀
     */
    public static final String OAUTH_KEY_CLIENT_DETAILS = "oauth:client:details";

    /**
     * 缓存 -- 用于tokenstore的存取前缀
     */
    public static final String OAUTH_PREFIX_KEY = "oauth";

    /**
     * 缓存 -- 用于tokenstore的存取前缀
     */
    public static final String OAUTH_CLIENT_CREDENTIALS = "client_credentials";
    /**
     * 缓存 -- 用户ID前缀
     */
    public static final String OAUTH_DETAILS_USER_ID = "user_id";
    /**
     * 缓存 -- 用户名称前缀
     */
    public static final String OAUTH_DETAILS_USERNAME = "user_name";

    /**
     * 缓存 -- 用户登录信息
     */
    public static final String OAUTH_DETAILS_LOGIN_INFO = "login_info";

    /**
     * 账户交易组， 默认组别
     */
    public static final String DEFAULT_GROUP_NAME = "默认组";

}
