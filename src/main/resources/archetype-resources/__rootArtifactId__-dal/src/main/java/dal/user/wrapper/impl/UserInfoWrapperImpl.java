#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.user.wrapper.impl;

import ${package}.client.common.error.ErrorWrapper;
import ${package}.client.common.exception.DatabaseSqlExecuteException;
import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.result.Result;
import ${package}.client.common.result.ResultFactory;
import ${package}.client.user.domain.UserInfoDomain;
import ${package}.client.user.query.UserInfoQuery;
import ${package}.dal.common.wrapper.MapperWrapper;
import ${package}.dal.user.mapper.UserInfoMapper;
import ${package}.dal.user.wrapper.UserInfoWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Created by ${userName} on ${today}.
 */
@Service("userInfoWrapper")
public class UserInfoWrapperImpl implements UserInfoWrapper {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<ListWrapper<UserInfoDomain>> findUserInfoOrderByName(Integer pageSize, Integer currentPage, Boolean asc) {
        UserInfoQuery query = new UserInfoQuery();
        query.setPageSize(pageSize);
        query.setCurrentPage(currentPage);
        query.putOrderBy("name", asc);
        return MapperWrapper.find(userInfoMapper, query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Integer> randomUpdateUsers(Integer pageSize, Integer currentPage, Boolean asc) {
        UserInfoQuery query = new UserInfoQuery();
        query.setPageSize(pageSize);
        query.setCurrentPage(currentPage);
        query.putOrderBy("name", asc);
        Result<ListWrapper<UserInfoDomain>> usersResult = MapperWrapper.find(userInfoMapper, query);
        if (!usersResult.hasValue()) {
            return ResultFactory.error(usersResult);
        }
        List<UserInfoDomain> list = usersResult.getData().getRows();
        Random random = new Random();
        int age = 0;
        int updateCount = 0;
        int updateSuccessCount = 0;
        for (UserInfoDomain userInfoDomain : list) {
            age = random.nextInt(20) + 10;
            if (age > 20) {
                userInfoDomain.setAge(age);
                Result<UserInfoDomain> userResult = MapperWrapper.update(userInfoMapper, userInfoDomain, new ErrorWrapper());
                if (userResult.isSuccess()) {
                    updateCount++;
                    if (userResult.notNull()) {
                        updateSuccessCount++;
                    }
                }
            }
        }
        System.out.printf("%s,%s%n", updateCount, updateSuccessCount);
        if(updateSuccessCount > 0){
            throw new DatabaseSqlExecuteException(new ErrorWrapper(0, "TEST_TRANSACTION", "test transaction"));
        }
        return ResultFactory.success(updateSuccessCount);
    }
}
