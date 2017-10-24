package aiidc.com.cn.portal.service;

import aiidc.com.cn.portal.entity.Overview;
import aiidc.com.cn.portal.parameter.OverviewModel;
import aiidc.com.cn.portal.util.Pagination;

import java.util.List;

/**
 * Created by Zhangx on 2017/9/21 at 10:36.
 */
public interface OverviewManager
{

    /**
     * 根据条件分页查询
     *
     * @param queryModel
     * @return
     */
     Pagination pageQuery(OverviewModel queryModel);

    /**
     * 根据条件查询所有list集合
     *
     * @param queryModel
     * @return
     */
     List<Overview> ListQuery(OverviewModel queryModel);

    /**
     * 添加一条记录
     *
     * @param entity
     */
     void add(Overview entity);

    /**
     * 修改一条记录
     *
     * @param entity
     */
     void update(Overview entity);

    /**
     * 根据ID查找一条记录
     *
     * @param entityId
     * @return
     */
     Overview findById(String entityId);

    /**
     * 根据ID删除一条记录
     *
     * @param entityId
     */
     void deleteById(String entityId);

    /**
     * 查询所有
     *
     * @return
     */
     List<Overview> getAll();

    /**
     * 根据属性值查询实体(属性值唯一，不重复)
     *
     * @param Property
     * @param value
     * @return
     */
     Overview findByProperty(String Property, Object value);
}
