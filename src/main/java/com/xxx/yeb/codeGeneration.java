package com.xxx.yeb;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis-plus 代码自动生成器
 */
public class codeGeneration {
    public static void main(String[] args){
        // 创建generator对象
        AutoGenerator autoGenerator = new AutoGenerator();
        // 设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123.abc");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/yeb?useUnicode=true&characterEncoding=UTF-8");
        autoGenerator.setDataSource(dataSourceConfig);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false);
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        globalConfig.setAuthor("ws");
        globalConfig.setServiceName("%sService");
        autoGenerator.setGlobalConfig(globalConfig);
        // 包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.xxx.yeb");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);
        // 配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 生成 @RestController 控制器
        strategyConfig.setRestControllerStyle(true);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute();
    }
}
