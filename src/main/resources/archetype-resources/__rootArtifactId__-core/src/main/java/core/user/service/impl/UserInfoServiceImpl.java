#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.user.service.impl;

import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.result.Result;
import ${package}.client.common.result.ResultFactory;
import ${package}.client.user.domain.UserInfoDomain;
import ${package}.core.user.service.UserInfoService;
import ${package}.dal.user.wrapper.UserInfoWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ${userName} on ${today}.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoWrapper userInfoWrapper;

    public Result<ListWrapper<String>> findUserNameByDesc(Integer pageSize, Integer currentPage) {
        Result<ListWrapper<UserInfoDomain>> result = userInfoWrapper.findUserInfoOrderByName(pageSize, currentPage, false);
        if (!result.hasValue()) {
            return ResultFactory.error(result);
        }
        ListWrapper<UserInfoDomain> value = result.getData();
        return ResultFactory.success(value.convert(val -> val.getName()));
    }
}
