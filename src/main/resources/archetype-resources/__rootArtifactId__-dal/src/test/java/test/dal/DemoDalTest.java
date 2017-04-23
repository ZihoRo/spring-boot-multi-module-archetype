#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.dal;

import ${package}.dal.DemoDal;
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
        classes = {DemoDal.class}
)
public class DemoDalTest {

    @Resource
    private DemoDal demoDal;

    @Test
    public void test(){
        System.out.println(demoDal);
    }
}
