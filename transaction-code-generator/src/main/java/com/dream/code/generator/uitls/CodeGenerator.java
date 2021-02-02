package com.dream.code.generator.uitls;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author Lv.QingYu
 */
public class CodeGenerator {

    public static void main(String[] args) {
        generate("com.dream.sharding", "sphere", "user_amount_info");
    }

    /**
     * 自动生成代码
     *
     * @param parentPackage 父级包名
     * @param modelName     模块名称
     * @param tableNames    要生成的表名
     */
    private static void generate(String parentPackage, String modelName, String... tableNames) {

        AutoGenerator autoGenerator = new AutoGenerator();
        // 1.1.全局配置
        GlobalConfig config = new GlobalConfig();
        // 文件保存位置
        config.setOutputDir("C:\\Users\\admin\\Desktop\\auto");
        // 每个类顶部注释的作者
        config.setAuthor("Lv.QingYu");
        // 默认生成后会打开文件所在位置，不想打开可以设置为false
        config.setOpen(false);
        autoGenerator.setGlobalConfig(config);

        // 1.2.策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名 -> 实体类名：使用驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库字段 -> 属性名：驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 使用 lombok
        strategy.setEntityLombokModel(true);
        // 使用 @RestController 注解
        strategy.setRestControllerStyle(true);
        // @RequestMapping 默认驼峰命名法 -> 改为用-分隔，更好看
        strategy.setControllerMappingHyphenStyle(true);
        // 将表上的注释映射到实体类
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 需要生成的表名 可变形参传入
        strategy.setInclude(tableNames);
        autoGenerator.setStrategy(strategy);

        // 2.1.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/auth-master?autoReconnect=true&useUnicode=true&createDatabaseIfNotExist=true&characterEncoding=utf8&useSSL=false&serverTimezone=CTT");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        autoGenerator.setDataSource(dataSourceConfig);

        // 2.2.包配置、controller、service、mapper、entity都可以这里设置
        PackageConfig pc = new PackageConfig();
        // 父级包路径
        pc.setParent(parentPackage);
        // 模块名称：生成 @RequestMapping 时的前缀
        pc.setModuleName(modelName);
        autoGenerator.setPackageInfo(pc);
        // 3.自动生成
        autoGenerator.execute();
    }
}
