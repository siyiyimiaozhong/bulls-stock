package com.siyi.bulls.stock.trade.auth.controller;


import com.siyi.bulls.stock.common.web.vo.Result;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author siyiyimiaozhong
 * @since 2022-11-13  23:25
 */
@RestController
@RequestMapping("/trade")
public class TradeUserController {
    private final UserDetailsService authStockUserDetailService;

    public TradeUserController(UserDetailsService authStockUserDetailService) {
        this.authStockUserDetailService = authStockUserDetailService;
    }

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    @RequestMapping("/user")
    public Result<UserDetails> getUser(@RequestParam("username")String username) {
        UserDetails userDetails = this.authStockUserDetailService.loadUserByUsername(username);
        return Result.success(userDetails);
    }
}
