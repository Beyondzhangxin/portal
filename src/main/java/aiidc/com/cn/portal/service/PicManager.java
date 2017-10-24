package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.PicModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 图片业务接口
 */
public interface PicManager {
	
	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(PicModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Pic> ListQuery(PicModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Pic entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Pic entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Pic findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Pic> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Pic findByProperty(String Property , Object value);
}
