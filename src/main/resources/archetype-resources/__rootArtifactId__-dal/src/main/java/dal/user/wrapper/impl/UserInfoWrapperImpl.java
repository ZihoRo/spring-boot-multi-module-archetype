#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.user.wrapper.impl;

import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.result.Result;
import ${package}.client.user.domain.UserInfoDomain;
import ${package}.client.user.query.UserInfoQuery;
import ${package}.dal.common.wrapper.MapperWrapper;
import ${package}.dal.user.mapper.UserInfoMapper;
import ${package}.dal.user.wrapper.UserInfoWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ${userName} on ${today}.
 */
@Service("userInfoWrapper")
public class UserInfoWrapperImpl implements UserInfoWrapper {
    @Resource
    private UserInfoMapper userInfoMapper;

    public Result<ListWrapper<UserInfoDomain>> findUserInfoOrderByName(Integer pageSize, Integer currentPage, boolean asc) {
        UserInfoQuery query = new UserInfoQuery();
        query.setPageSize(pageSize);
        query.setCurrentPage(currentPage);
        query.putOrderBy("name", asc);
        return MapperWrapper.find(userInfoMapper, query);
    }
}
