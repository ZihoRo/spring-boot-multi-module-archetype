#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by ${user.name} on ${date.get('yyyy/M/d')}.
 */
@SpringBootApplication(scanBasePackages = {
        "com.shinemo.demo.web.controller",
        "com.shinemo.demo.web.config",
        "com.shinemo.demo.core.service.impl"
})
@MapperScan("com.shinemo.demo.dal.mapper")
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServletInitializer.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServletInitializer.class, args);
    }

}
