#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.core;

import ${package}.core.DemoCore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by ${userName} on ${today}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoCore.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoCoreTest {

    @Resource
    private DemoCore demoCore;

    @Test
    public void test(){
        System.out.println(demoCore);
    }
}
