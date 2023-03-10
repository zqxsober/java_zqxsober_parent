package com.zqxsober.common.generator.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zqxsober.common.generator.params.GenerateParams;

import java.io.File;

/**
 * 简单代码生成器
 * @author zqxsober
 */
public class SimpleGenerator {


    /**
     * 代码生成
     * <pre>
     *     此处为基础配置
     *     高级配置可在 doGeneration() 修改
     * </pre>
     */
    public static void main(String[] args) {

        // jdbc连接地址
        String jdbcUrl = "jdbc:mysql://81.68.249.232:3306/";

        // 数据库用户名
        String jdbcUserName = "root";

        // 数据库密码
        String jdbcPwd = "zqxsober";

        // 注释的作者
        String author = "zqxsober";

        // 父级包路径
        String parentPackageName = "com.zqxsober.micro.service.";

        // 数据库前缀
        String dbNamePrefix = "zqxsober_";

        // 模块名
        String module = "message";

        // 数据库名
        String dbName = dbNamePrefix + module;

        GenerateParams generateParams = new GenerateParams();

        // 代码生成输出的目录，可为项目路径的相对路径，此处设置为桌面
        generateParams.setOutputDirectory(System.getProperty("user.home") + File.separator + "Desktop/");

        // jdbc 地址
        generateParams.setJdbcUrl(jdbcUrl + dbName + "?tinyInt1isBit=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=Asia/Shanghai");

        // 数据库账号
        generateParams.setJdbcUserName(jdbcUserName);

        // 数据库密码
        generateParams.setJdbcPassword(jdbcPwd);

        // 代码生成包含的表，可为空，为空默认生成所有
        generateParams.setIncludeTables(new String[]{

        });

        // 代码生成的类的父包名称
        generateParams.setParentPackage(parentPackageName + module);

        // 生成代码里，注释的作者
        generateParams.setAuthor(author);

        // 执行生成方法
        SimpleGenerator.doGeneration(generateParams);
    }

    public static void doGeneration(GenerateParams generateParams) {
        AutoGenerator mpg = new AutoGenerator();

        // 1.全局配置
        GlobalConfig gc = assembleConfig(generateParams);
        mpg.setGlobalConfig(gc);

        // 2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl(generateParams.getJdbcUrl());
        dsc.setUsername(generateParams.getJdbcUserName());
        dsc.setPassword(generateParams.getJdbcPassword());
        dsc.setTypeConvert(new MySqlTypeConvertCustom());
        mpg.setDataSource(dsc);

        // 3.策略配置
        StrategyConfig strategy = assembleStrategy(generateParams);
        mpg.setStrategy(strategy);

        // 4.目标配置
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(new TemplateConfig());

        // 5.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(generateParams.getParentPackage());
        pc.setEntity("pojo.po");
        pc.setXml("src.main.resources.mapper");
        pc.setServiceImpl("service.impl");
        pc.setService("service");
        mpg.setPackageInfo(pc);

        // 6.执行生成
        mpg.execute();
    }

    private static StrategyConfig assembleStrategy(GenerateParams generateParams) {

        StrategyConfig strategy = new StrategyConfig();

        // 是否大写命名
        strategy.setCapitalMode(false);

        // 此处可以移除表前缀表前缀
        strategy.setTablePrefix(generateParams.getRemoveTablePrefix());

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 需要生成的表
        strategy.setInclude(generateParams.getIncludeTables());

        // 使用 Lombok
        strategy.setEntityLombokModel(true);

        // 使用构建者模式
        strategy.setEntityBuilderModel(true);

        // 生成注释
        strategy.setEntityTableFieldAnnotationEnable(true);
        return strategy;
    }

    private static GlobalConfig assembleConfig(GenerateParams generateParams) {

        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();

        // 输出目录
        gc.setOutputDir(generateParams.getOutputDirectory());

        // 是否覆盖已有文件
        gc.setFileOverride(true);

        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);

        // 是否打开输出目录
        gc.setOpen(true);

        // 作者
        gc.setAuthor(generateParams.getAuthor());

        // id 类型
        gc.setIdType(IdType.INPUT);

        // date 类型
        gc.setDateType(DateType.ONLY_DATE);


        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        if (generateParams.getGeneratorInterface()) {
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sServiceImpl");
        } else {
            gc.setServiceName("%sService");
            gc.setServiceImplName("%sService");
        }
        return gc;
    }
}

