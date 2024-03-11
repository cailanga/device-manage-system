package cn.pzhxy.devicemanager.base.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

//@Configuration
//@EnableSwagger2  //2.x用 @EnableSwagger2WebMvc
//@EnableSwagger2WebMvc

@EnableKnife4j
@Configuration
@ConditionalOnProperty(prefix = "knife4j",name = "enable",havingValue = "true")
public class Knife4jConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("攀枝花学院物资设备管理系统接口文档")
                .description("攀枝花学院物资设备管理系统接口文档，仅限内部使用")
                .termsOfServiceUrl("http://www.lvjiaxin.org")
                .contact(new Contact("lvjiaxin", "www.lvjiaxin.org", "2026972757@qq.com"))
                .version("1.0")
                .build();
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的全局鉴权策略
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    @Bean
    public Docket defaultApi2() {
        Predicate<RequestHandler> predicate = RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.auth.controller")
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.device.controller"))
                // 继续添加其他包路径
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.goods.controller"))
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.notice.controller"))
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.org.controller"))
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.record.controller"))
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.seller.controller"))
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.statistics.controller"))
                .or(RequestHandlerSelectors.basePackage("cn.pzhxy.devicemanager.sys.controller"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                //.groupName("1.0版本")
                .select()
                //指定controller（接口）扫描的包路径
                .apis(predicate)
                .paths(PathSelectors.any()) //swagger2.0时用
//                .paths(regex("/.*"))
                .build()
                // 安全模式
                .securitySchemes(securitySchemes())
                // 安全上下文
                .securityContexts(securityContexts())
                .pathMapping("/");
    }

}