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
        return count(mapper, query, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<Long> count(Mapper<E, M, Q> mapper, Q query, ErrorWrapper error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10000, "MAPPER_NULL_COUNT", "mapper is null"));
        }
        if (query == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10001, "QUERY_NULL_COUNT", "query is null"));
        }
        try {
            return ResultFactory.success(mapper.count(query));
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorWrapper(10002, "SQL_ERROR_COUNT", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<ListWrapper<M>> find(Mapper<E, M, Q> mapper, Q query) {
        return find(mapper, query, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<ListWrapper<M>> find(Mapper<E, M, Q> mapper, Q query, ErrorWrapper error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10003, "MAPPER_NULL_FIND", "mapper is null"));
        }
        if (query == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10004, "QUERY_NULL_FIND", "query is null"));
        }
        try {
            if (query.isPageEnable()) {
                long count = mapper.count(query);
                if (count < 1) {
                    return ResultFactory.successList();
                }
                query.putTotalCount(count);
            }
            return ResultFactory.successList(mapper.find(query), query.getTotalCount(), query.getCurrentPage(), query.getPageSize());
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorWrapper(10005, "SQL_ERROR_FIND", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<M> get(Mapper<E, M, Q> mapper, Q query) {
        return get(mapper, query, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<M> get(Mapper<E, M, Q> mapper, Q query, ErrorWrapper error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10006, "MAPPER_NULL_GET", "mapper is null"));
        }
        if (query == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10007, "QUERY_NULL_GET", "query is null"));
        }
        try {
            return ResultFactory.successCheck(mapper.get(query), error);
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorWrapper(10008, "SQL_ERROR_GET", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> insert(Mapper<E, M, Q> mapper, E entity) {
        return insert(mapper, entity, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> insert(Mapper<E, M, Q> mapper, E entity, ErrorWrapper error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10009, "MAPPER_NULL_INSERT", "mapper is null"));
        }
        if (entity == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10010, "ENTITY_NULL_INSERT", "entity is null"));
        }
        try {
            mapper.insert(entity);
            return ResultFactory.success(entity);
        } catch (Throwable e) {
            if (error == null) {
                error = new ErrorWrapper(10011, "SQL_ERROR_INSERT", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> update(Mapper<E, M, Q> mapper, E entity) {
        return update(mapper, entity, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> update(Mapper<E, M, Q> mapper, E entity, ErrorWrapper error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10012, "MAPPER_NULL_UPDATE", "mapper is null"));
        }
        if (entity == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10013, "ENTITY_NULL_UPDATE", "entity is null"));
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
            if (error == null) {
                error = new ErrorWrapper(10014, "SQL_ERROR_UPDATE", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> delete(Mapper<E, M, Q> mapper, E entity) {
        return delete(mapper, entity, null);
    }

    public static <E extends Entity, M extends Model, Q extends Query> Result<E> delete(Mapper<E, M, Q> mapper, E entity, ErrorWrapper error) {
        if (mapper == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10015, "MAPPER_NULL_DELETE", "mapper is null"));
        }
        if (entity == null) {
            throw new ParamterInvalidException(new ErrorWrapper(10016, "ENTITY_NULL_DELETE", "entity is null"));
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
            if (error == null) {
                error = new ErrorWrapper(10017, "SQL_ERROR_DELETE", "sql failed execute");
            }
            throw new DatabaseSqlExecuteException(error, e);
        }
    }
}
