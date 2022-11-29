package com.siyi.mybatis.generator.startup;

import com.siyi.mybatis.generator.custom.CustomCommentGenerator;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: siyiyimiaozhong
 * @Project: bulls-stock-backend
 * @Package: com.siyi.mybatis.generator.startup
 * @CreateTime: 2022-10-24  22:59
 * @Version: 1.0
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        List<String> tableNames = Arrays.asList("t_trade_user");
        String modelName = "trade.auth";
        CustomCommentGenerator generator = new CustomCommentGenerator(modelName, tableNames);
        generator.generateCode();
    }
}