#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.mapper;

import ${package}.client.common.entity.Entity;
import ${package}.client.common.model.Model;
import ${package}.client.common.query.Query;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public interface Mapper<E extends Entity, M extends Model, Q extends Query> {
    long count(Q query);

    List<M> find(Q query);

    M get(Q query);

    long insert(E value);

    long batchInsert(List<E> value);

    long update(E value);

    long delete(E value);

    void create(@Param("tableName") String tableName);
}
