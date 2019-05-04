package cn.ripple.service;

import cn.ripple.dao.BaseDao;
import cn.ripple.entity.BaseEntity;
import cn.ripple.utils.SpecificationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * 基础服务类
 * @Author Edwin
 * @param <E>
 * @param <ID>
 */
@FunctionalInterface
public interface BaseService<E extends BaseEntity, ID extends Serializable> {

    /**
     * 获取资源
     * @return
     */
    BaseDao<E, ID> getRepository();

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    default E get(ID id) {
        return getRepository().getOne(id);
    }

    /**
     * 获取所有列表
     *
     * @return
     */
    default List<E> getAll() {
        return getRepository().findAll();
    }

    /**
     * 获取总数
     *
     * @return
     */
    default Long getTotalCount() {
        return getRepository().count();
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    default E save(E entity) {

        return getRepository().save(entity);
    }

    /**
     * 修改
     *
     * @param entity
     * @return
     */
    default E update(E entity) {
        return getRepository().saveAndFlush(entity);
    }

    /**
     * 批量保存与修改
     *
     * @param entities
     * @return
     */
    default Iterable<E> saveOrUpdateAll(Iterable<E> entities) {
        return getRepository().saveAll(entities);
    }

    /**
     * 删除
     *
     * @param entity
     */
    default void delete(E entity) {
        getRepository().delete(entity);
    }

    /**
     * 根据Id删除
     *
     * @param id
     */
    default void delete(ID id) {
        getRepository().deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param entities
     */
    default void delete(Iterable<E> entities) {
        getRepository().deleteAll(entities);
    }

    /**
     * 清空缓存，提交持久化
     */
    default void flush() {
        getRepository().flush();
    }

    /**
     * 根据条件查询获取
     *
     * @param entity
     * @return
     */
    default List<E> findAll(E entity) {
        return getRepository().findAll(new SpecificationUtil().entityToSpecification(entity));
    }

    /**
     * 根据条件查询获取
     *
     * @param entity
     * @return
     */
    default E findOne(E entity) {
        try {
            return (E) getRepository().findOne(new SpecificationUtil().entityToSpecification(entity)).get();
        }catch(Exception e){
            return null;
        }
    }


    /**
     * 分页查询
     * @param entity
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param order
     * @return
     */
    default Page<E> page(E entity, int pageNum, int pageSize, String orderBy, Sort.Direction order) {
        Pageable pageable =  PageRequest.of(pageNum-1,pageSize,new Sort(order, orderBy));
        return getRepository().findAll(new SpecificationUtil().entityToSpecification(entity), pageable);
    }

    /**
     * @param entity
     * @param pageNum
     * @param pageSize
     * @return
     */
    default Page<E> page(E entity, int pageNum, int pageSize) {
        return this.page(entity,pageNum,pageSize,"id",Sort.Direction.DESC);
    }


    /**
     * @param entity
     * @return
     */
    default long count(E entity) {
        return getRepository().count(new SpecificationUtil().entityToSpecification(entity));
    }
}
