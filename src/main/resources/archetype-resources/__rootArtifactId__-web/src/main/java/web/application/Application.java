#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by ${userName} on ${today}.
 */
@SpringBootApplication(scanBasePackages = {
        "${package}.web.controller",
        "${package}.web.config",
        "${package}.core.service.impl"
})
@MapperScan("${package}.dal.mapper")
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
