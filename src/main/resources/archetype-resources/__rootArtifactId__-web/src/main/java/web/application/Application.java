#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by ${userName} on ${today}.
 */
@SpringBootApplication(scanBasePackages = {
        "${package}.web.configuration",
        "${package}.web.controller",
}, exclude = {
        /**
         * application.(properties|yaml) 配置文件的自动配置类
         */
//        PropertyPlaceholderAutoConfiguration.class,


        /**
         * application.(properties|yaml) 配置文件 spring.datasource.* 中的数据库连接相关配置的自动配置类
         */
//        DataSourceAutoConfiguration.class,
        /**
         * 数据库连接池相关配置的自动配置类
         */
//        DataSourcePoolMetadataProvidersConfiguration.class,
        /**
         * application.(properties|yaml) 配置文件 spring.datasource.* 中的数据库事务相关配置的自动配置类
         */
//        DataSourceTransactionManagerAutoConfiguration.class,
        /**
         * application.(properties|yaml) 配置文件 mybatis.* 中 mybatis 相关配置的自动配置类
         */
//        MybatisAutoConfiguration.class,
        /**
         * 数据库 jdbc 模板的自动配置类
         */
        JdbcTemplateAutoConfiguration.class,
        /**
         * application.(properties|yaml) 配置文件 spring.transaction.* 事务管理相关配置的自动配置类
         */
//        TransactionAutoConfiguration.class,
        /**
         * spring 事务 aop 的自动配置类, spring.dao.exceptiontranslation.enabled=true(default) | false
         */
//        PersistenceExceptionTranslationAutoConfiguration.class,


        /**
         * spring mvc 请求分发的自动配置类
         */
//        DispatcherServletAutoConfiguration.class,
        /**
         * application.(properties|yaml) 配置文件 spring.resources.* 中错误页面相关配置的自动配置类
         */
//        ErrorMvcAutoConfiguration.class,
        /**
         * application.(properties|yaml) 配置文件 spring.http.encoding.* 相关配置的自动配置类
         */
//        HttpEncodingAutoConfiguration.class,
        /**
         * spring mvc ResponseBody 转换的自动配置类
         */
//        HttpMessageConvertersAutoConfiguration.class,
        /**
         * spring mvc ResponseBody Jackson 转换的自动配置类
         */
//        JacksonAutoConfiguration.class,
        /**
         * spring.http.multipart.enabled=true(default) | false 设置
         * application.(properties|yaml) 配置文件 spring.http.multipart.* 上传文件相关配置的自动配置类
         */
//        MultipartAutoConfiguration.class,
        /**
         * spring mvc restful controller 的自动配置类
         */
//        WebClientAutoConfiguration.class,
        /**
         * spring mvc 的自动配置类
         */
//        WebMvcAutoConfiguration.class,
        /**
         * web socket 的自动配置类
         */
        WebSocketAutoConfiguration.class,
        /**
         * 本地启动 Servlet 容器的自动配置类
         */
//        EmbeddedServletContainerAutoConfiguration.class,
        /**
         * application.(properties|yaml) 配置文件 server.* 相关配置的自动配置类
         */
//        ServerPropertiesAutoConfiguration.class,


        /**
         * bean 属性校验、参数校验的自动配置类
         */
        ValidationAutoConfiguration.class,


        /**
         * spring.jmx.enabled=true(default) | false
         * application.(properties|yaml) 配置文件 spring.jmx.* Java 管理扩展 (jmx) 相关配置的自动配置类
         */
        JmxAutoConfiguration.class,
})
public class Application extends SpringBootServletInitializer {

    private static final Class<Application> application = Application.class;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.application);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(application, args);
    }

}
