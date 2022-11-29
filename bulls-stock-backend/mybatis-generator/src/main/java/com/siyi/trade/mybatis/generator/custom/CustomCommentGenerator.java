package com.siyi.trade.mybatis.generator.custom;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.siyi.trade.mybatis.generator.constant.GeneratorConstant;

import java.util.Collections;
import java.util.List;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.mybatis.generator.custom
 * @CreateTime: 2022-10-24  22:53
 * @Version: 1.0
 */
public class CustomCommentGenerator {

    /**
     * 模块名
     */
    private final String moduleName;
    /**
     * 对应表名
     */
    private final List<String> tableNames;

    public CustomCommentGenerator(String moduleName, List<String> tableNames) {
        this.moduleName = moduleName;
        this.tableNames = tableNames;
    }

    /**
     * 生成代码
     */
    public void generateCode() {
        FastAutoGenerator.create(
                        String.format(GeneratorConstant.DB_URL, GeneratorConstant.DB_NAME),
                        GeneratorConstant.USERNAME,
                        GeneratorConstant.PASSWORD
                ).globalConfig(builder -> {
                    builder.author("siyiyimiaozhong")                     // 作者
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java")    // 输出路径(写到java目录)
                            .enableSwagger()                    // 开启swagger，记得引入依赖并添加配置文件swagger2
                            .commentDate("yyyy-MM-dd  HH:mm")
                            .fileOverride();                    // 开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("com.siyi.bulls.stock")                  // /src/main/java/***
                            .moduleName(this.moduleName)    // /src/main/java/↑↑↑/***
                            .entity("pojo.entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(this.tableNames)
                            .addTablePrefix("t_")               // 过滤表名前缀
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()                     // 开启Lombok
                            .logicDeleteColumnName("deleted")   // 逻辑删除字段名
                            .enableTableFieldAnnotation()       // .versionColumnName("version") // 乐观锁字段名
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()                  // 开启RestController
                            .mapperBuilder()
                            .enableBaseResultMap()              // 生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}