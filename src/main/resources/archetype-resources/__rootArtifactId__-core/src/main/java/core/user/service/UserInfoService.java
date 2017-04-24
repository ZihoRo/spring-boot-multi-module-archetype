#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.user.service;

import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.result.Result;

/**
 * Created by ${userName} on ${today}.
 */
public interface UserInfoService {
    Result<ListWrapper<String>> findUserNameByDesc(Integer pageSize, Integer currentPage);
}
