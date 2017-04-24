#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.mapper;

import ${package}.client.common.domain.Domain;
import ${package}.client.common.query.Query;

/**
 * Created by ${userName} on ${today}.
 */
public interface DomainMapper<D extends Domain, Q extends Query> extends Mapper<D, D, Q> {
}
