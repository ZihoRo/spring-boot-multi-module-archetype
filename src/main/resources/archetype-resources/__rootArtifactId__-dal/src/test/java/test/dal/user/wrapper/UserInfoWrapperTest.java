#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.dal.user.wrapper;

import ${package}.client.user.query.UserInfoQuery;
import ${package}.dal.user.mapper.UserInfoMapper;
import ${package}.dal.user.wrapper.UserInfoWrapper;
import ${package}.test.dal.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by ${userName} on ${today}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class})
public class UserInfoWrapperTest {
    @Resource
    private UserInfoWrapper userInfoWrapper;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void findUserInfoOrderByName() {
        Integer pageSize = null;
        Integer currentPage = null;
        Boolean asc = null;
        System.out.printf("pageSize=%s, currentPage=%s, asc=%s, result=%s%n",
                pageSize,
                currentPage,
                asc,
                userInfoWrapper.findUserInfoOrderByName(pageSize, currentPage, asc)
        );
    }

    @Test
    public void randomUpdateUsers() {
        UserInfoQuery query = new UserInfoQuery();
        String before = String.format("param=%s, result=%s%n", query, userInfoMapper.find(query));
        System.out.printf(before);
        Integer pageSize = null;
        Integer currentPage = null;
        Boolean asc = null;
        try {
            System.out.printf("pageSize=%s, currentPage=%s, asc=%s, result=%s%n",
                    pageSize,
                    currentPage,
                    asc,
                    userInfoWrapper.randomUpdateUsers(pageSize, currentPage, asc)
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        String after = String.format("param=%s, result=%s%n", query, userInfoMapper.find(query));
        System.out.println(after.equals(before));
    }
}
