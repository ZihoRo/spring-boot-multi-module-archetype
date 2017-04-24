#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.wrapper;

import ${package}.client.common.entity.Entity;
import ${package}.client.common.error.ErrorWrapper;
import ${package}.client.common.exception.DatabaseSqlExecuteException;
import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.model.Model;
import ${package}.client.common.query.Query;
import ${package}.client.common.result.Result;
import ${package}.client.common.result.ResultFactory;
import ${package}.dal.common.mapper.Mapper;

/**
 * Created by ${userName} on ${today}.
 */
public class MapperWrapper {
    public static <E extends Entity, M extends Model, Q extends Query> Result<Long> count(Mapper<E, M, Q> mapper, Q query) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        }
        if (query == null) {
            throw new NullPointerException("query is null");
        }
        try {
            return ResultFactory.success(mapper.count(query));
        } catch (Throwable e) {
            throw new DatabaseSqlExecuteException(e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<ListWrapper<M>> find(Mapper<E, M, Q> mapper, Q query) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        }
        if (query == null) {
            throw new NullPointerException("query is null");
        }
        try {
            long count = mapper.count(query);
            if (count < 1) {
                return ResultFactory.successList();
            }
            if(query.isPageEnable()){
                query.putTotalCount(count);
            }
            return ResultFactory.successList(mapper.find(query), count);
        } catch (Throwable e) {
            throw new DatabaseSqlExecuteException(e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<M> get(Mapper<E, M, Q> mapper, Q query) {
        return get(mapper, query, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<M> get(Mapper<E, M, Q> mapper, Q query, ErrorWrapper error) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        }
        if (query == null) {
            throw new NullPointerException("query is null");
        }
        try {
            return ResultFactory.successCheck(mapper.get(query), error);
        } catch (Throwable e) {
            throw new DatabaseSqlExecuteException(e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> insert(Mapper<E, M, Q> mapper, E entity) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        }
        if (entity == null) {
            throw new NullPointerException("entity is null");
        }
        try {
            mapper.insert(entity);
            return ResultFactory.success(entity);
        } catch (Throwable e) {
            throw new DatabaseSqlExecuteException(e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> update(Mapper<E, M, Q> mapper, E entity) {
        return update(mapper, entity, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> update(Mapper<E, M, Q> mapper, E entity, ErrorWrapper error) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        }
        if (entity == null) {
            throw new NullPointerException("entity is null");
        }
        try {
            long result = mapper.update(entity);
            if (result < 1) {
                if (error == null) {
                    return ResultFactory.success(entity);
                }
                return ResultFactory.success(error);
            }
            return ResultFactory.success(entity);
        } catch (Throwable e) {
            throw new DatabaseSqlExecuteException(e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> delete(Mapper<E, M, Q> mapper, E entity) {
        return delete(mapper, entity, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> delete(Mapper<E, M, Q> mapper, E entity, ErrorWrapper error) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        }
        if (entity == null) {
            throw new NullPointerException("entity is null");
        }
        try {
            long result = mapper.delete(entity);
            if (result < 1) {
                if (error == null) {
                    return ResultFactory.success(entity);
                }
                return ResultFactory.success(error);
            }
            return ResultFactory.success(entity);
        } catch (Throwable e) {
            throw new DatabaseSqlExecuteException(e);
        }
    }
}
