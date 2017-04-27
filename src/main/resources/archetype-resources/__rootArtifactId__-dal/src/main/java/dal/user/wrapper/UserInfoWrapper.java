#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.user.wrapper;

import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.result.Result;
import ${package}.client.user.domain.UserInfoDomain;

/**
 * Created by ${userName} on ${today}.
 */
public interface UserInfoWrapper {
    Result<ListWrapper<UserInfoDomain>> findUserInfoOrderByName(Integer pageSize, Integer currentPage, Boolean asc);

    Result<Integer> randomUpdateUsers(Integer pageSize, Integer currentPage, Boolean asc);
}
