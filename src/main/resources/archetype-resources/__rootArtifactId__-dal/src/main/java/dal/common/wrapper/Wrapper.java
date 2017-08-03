#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.common.wrapper;

import ${package}.client.common.entity.Entity;
import ${package}.client.common.error.ErrorWrapper;
import ${package}.client.common.list.ListWrapper;
import ${package}.client.common.model.Model;
import ${package}.client.common.query.Query;
import ${package}.client.common.result.Result;
import ${package}.dal.common.mapper.Mapper;

import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public abstract class Wrapper<E extends Entity, M extends Model, Q extends Query> {
    protected abstract Mapper<E, M, Q> getMapper();

    public Result<Long> count(Q query) {
        return MapperWrapper.count(getMapper(), query);
    }

    public Result<Long> count(Q query, ErrorWrapper error) {
        return MapperWrapper.count(getMapper(), query, error);
    }

    public Result<ListWrapper<M>> find(Q query) {
        return MapperWrapper.find(getMapper(), query);
    }

    public Result<ListWrapper<M>> find(Q query, ErrorWrapper error) {
        return MapperWrapper.find(getMapper(), query, error);
    }

    public Result<M> get(Q query) {
        return MapperWrapper.get(getMapper(), query);
    }

    public Result<M> get(Q query, ErrorWrapper error) {
        return MapperWrapper.get(getMapper(), query, error);
    }

    public Result<E> insert(E entity) {
        return MapperWrapper.insert(getMapper(), entity);
    }

    public Result<E> insert(E entity, ErrorWrapper error) {
        return MapperWrapper.insert(getMapper(), entity, error);
    }

    public Result<Long> batchInsert(List<E> entities) {
        return MapperWrapper.batchInsert(getMapper(), entities);
    }

    public Result<Long> batchInsert(List<E> entities, ErrorWrapper error) {
        return MapperWrapper.batchInsert(getMapper(), entities, error);
    }

    public Result<E> update(E entity) {
        return MapperWrapper.update(getMapper(), entity);
    }

    public Result<E> update(E entity, ErrorWrapper error) {
        return MapperWrapper.update(getMapper(), entity, error);
    }

    public Result<E> delete(E entity) {
        return MapperWrapper.delete(getMapper(), entity);
    }

    public Result<E> delete(E entity, ErrorWrapper error) {
        return MapperWrapper.delete(getMapper(), entity, error);
    }

    public Result<Void> create(String tableName) {
        return MapperWrapper.create(getMapper(), tableName);
    }

    public Result<Void> create(String tableName, ErrorWrapper error) {
        return MapperWrapper.create(getMapper(), tableName, error);
    }
}