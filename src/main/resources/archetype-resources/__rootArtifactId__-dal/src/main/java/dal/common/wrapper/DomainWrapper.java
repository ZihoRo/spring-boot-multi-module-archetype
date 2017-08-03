#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.wrapper;

import ${package}.client.common.domain.Domain;
import ${package}.client.common.query.Query;

/**
 * Created by ${userName} on ${today}.
 */
public abstract class DomainWrapper<D extends Domain, Q extends Query> extends Wrapper<D, D, Q> {
}