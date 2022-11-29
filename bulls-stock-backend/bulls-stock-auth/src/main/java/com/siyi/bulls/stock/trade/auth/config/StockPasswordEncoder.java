package com.siyi.bulls.stock.trade.auth.config;

import com.siyi.bulls.stock.common.encrypt.EncryptUtil;
import com.siyi.bulls.stock.common.exception.ComponentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.bulls.stock.trade.auth.config
 * @ClassName: StockPasswordEncoder.java
 * @CreateTime: 2022-11-17  12:40
 * @Version: 1.0
 */
@Service
@Slf4j
public class StockPasswordEncoder implements PasswordEncoder {

    /**
     * 密码编码处理
     *
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * 密码校验判断处理
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword != null && rawPassword.length() > 0) {
            try {
                // 这里通过MD5及B64加密
                String password = EncryptUtil.encryptSigned(rawPassword.toString());
                boolean isMatch = encodedPassword.equals(password);
                if (!isMatch) {
                    log.warn("password 不一致！");
                }
                return isMatch;
            } catch (ComponentException e) {
                log.error(e.getMessage(), e);
            }
        }
        return false;
    }
}
