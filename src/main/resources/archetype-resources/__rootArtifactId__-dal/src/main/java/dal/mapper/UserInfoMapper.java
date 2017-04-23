#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.mapper;

import ${package}.client.user.domain.UserInfoDomain;
import ${package}.client.user.query.UserInfoQuery;

import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public interface UserInfoMapper {
    long count(UserInfoQuery query);

    List<UserInfoDomain> find(UserInfoQuery query);

    UserInfoDomain get(UserInfoQuery query);

    long insert(UserInfoDomain value);

    long update(UserInfoDomain value);

    long delete(UserInfoDomain value);
}
