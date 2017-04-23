#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.dal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by ${userName} on ${today}.
 */
@SpringBootApplication
@MapperScan(basePackages = {"${package}.dal.mapper"})
public class ApplicationTest {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationTest.class, args);
    }
}
