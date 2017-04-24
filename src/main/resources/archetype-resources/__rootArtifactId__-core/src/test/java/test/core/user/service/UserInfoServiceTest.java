#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.core.user.service;

import ${package}.test.core.ApplicationTest;
import ${package}.core.user.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by ${userName} on ${today}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {ApplicationTest.class}
)
public class UserInfoServiceTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    public void test(){
        System.out.println(userInfoService);
    }
}
