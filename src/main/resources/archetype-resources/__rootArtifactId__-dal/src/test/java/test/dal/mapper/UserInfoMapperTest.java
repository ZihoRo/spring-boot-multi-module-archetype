#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.test.dal.mapper;

import ${package}.client.user.domain.UserInfoDomain;
import ${package}.client.user.query.UserInfoQuery;
import ${package}.dal.mapper.UserInfoMapper;
import ${package}.test.dal.ApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ${userName} on ${today}.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class})
public class UserInfoMapperTest {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void count() {
        UserInfoQuery query = new UserInfoQuery();
        System.out.printf("param=%s, result=%s%n", query, userInfoMapper.count(query));
    }

    @Test
    public void find() {
        UserInfoQuery query = new UserInfoQuery();
        System.out.printf("param=%s, result=%s%n", query, userInfoMapper.find(query));
    }

    @Test
    public void get() {
        UserInfoQuery query = new UserInfoQuery();
        List<UserInfoDomain> list = userInfoMapper.find(query);
        Assert.assertNotNull("list == null", list);
        Assert.assertFalse(list.isEmpty());
        query = UserInfoQuery.builder().id(list.get(list.size() - 1).getId()).build();
        System.out.printf("param=%s, result=%s%n", query, userInfoMapper.find(query));
    }

    @Test
    public void insert() {
        UserInfoDomain value = UserInfoDomain.builder().name(UUID.randomUUID().toString()).age(new Random().nextInt(100)).build();
        System.out.printf("param=%s, result=%s%n", value, userInfoMapper.insert(value));
    }

    @Test
    public void update() {
        UserInfoQuery query = new UserInfoQuery();
        List<UserInfoDomain> list = userInfoMapper.find(query);
        Assert.assertNotNull("list == null", list);
        Assert.assertFalse(list.isEmpty());
        UserInfoDomain value = UserInfoDomain.builder().id(list.get(list.size() - 1).getId()).age(new Random().nextInt(100)).build();
        System.out.printf("param=%s, result=%s%n", value, userInfoMapper.update(value));
    }

    @Test
    public void delete() {
        UserInfoQuery query = new UserInfoQuery();
        List<UserInfoDomain> list = userInfoMapper.find(query);
        Assert.assertNotNull("list == null", list);
        Assert.assertFalse(list.isEmpty());
        UserInfoDomain value = UserInfoDomain.builder().id(list.get(0).getId()).build();
        System.out.printf("param=%s, result=%s%n", value, userInfoMapper.delete(value));
    }
}
