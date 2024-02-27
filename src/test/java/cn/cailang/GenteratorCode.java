package cn.cailang;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * Created by CDHong on 2018/4/6.
 */
public class GenteratorCode {

    public static void main(String[] args) throws InterruptedException {
        //用来获取Mybatis-Plus.properties文件的配置信息
        ResourceBundle rb = ResourceBundle.getBundle("mybatitsPlus-config"); //不要加后缀
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rb.getString("OutputDir"));
        gc.setFileOverride(true);
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor(rb.getString("author"));
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName(rb.getString("jdbc.driver"));
        dsc.setUsername(rb.getString("jdbc.user"));
        dsc.setPassword(rb.getString("jdbc.pwd"));
        dsc.setUrl(rb.getString("jdbc.url"));
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] {
                "t_sys",
//                "t_mkt",
//                "t_customer",
//                "t_finance",
//                "t_aftermarket",
//                "t_prod",
//                "t_sys"
        });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{
//                "t_aftermarket_guarantee",
//                "t_aftermarket_guarantee_item",
//                "t_finance_deposit",
//                "t_finance_receivables",
//                "t_finance_receivables_item",
//                "t_finance_receivables_remark",
//                "t_mkt_activity",
//                "t_mkt_activity_remark",
//                "t_mkt_business",
//                "t_mkt_business_product",
//                "t_mkt_business_remark",
//                "t_mkt_clue",
//                "t_mkt_clue_activity",
//                "t_mkt_clue_remark",
//                "t_customer_customer",
//                "t_customer_customer_remark",
//                "t_order_contract",
//                "t_order_contract_remark",
//                "t_order_order",
//                "t_order_order_activity",
//                "t_order_order_product",
//                "t_prod_product",
//                "t_prod_product_type",
//                "t_sys_config",
//                "t_sys_dictionary",
//                "t_sys_dictionaryitem"
//                "t_maintain",
                "t_sys_backup",
                "t_sys_backup_operator_log"
        }); // 需要生成的表
        strategy.setSuperEntityClass("cn.cailang.base.domain.BaseDomain");
        strategy.setSuperServiceClass("cn.cailang.base.service.IBaseService");
        strategy.setSuperServiceImplClass("cn.cailang.base.service.impl.BaseServiceImpl");
        strategy.setSuperMapperClass("cn.cailang.base.mapper.BaseMapper");
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parent"));
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("domain");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                map.put("parent", pc.getParent());
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();

        String parentPath = "/"+pc.getParent().replaceAll("\\.","/")+"/";
        System.out.println(parentPath);
        // 调整 domain 生成目录演示
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDir")+ parentPath+"domain/" + tableInfo.getEntityName() + ".java";
            }
        });
        //query
        focList.add(new FileOutConfig("/templates/query.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDir")+ parentPath+"query/" + tableInfo.getEntityName() + "Query.java";
            }
        });

        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return rb.getString("OutputDirXml")+ parentPath+"mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setService("/templates/service.java.vm");
        tc.setServiceImpl("/templates/serviceImpl.java.vm");
        tc.setEntity("/templates/entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        //tc.setController(null);
        tc.setXml(null);
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();
    }

}