#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.dal.user.wrapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ${userName} on ${today}.
 */
@SpringBootApplication
@MapperScan(basePackages = {
        "${package}.dal.user.mapper"
})
@ComponentScan(basePackages = {
        "${package}.dal.user.wrapper",
})
public class ApplicationTest {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
