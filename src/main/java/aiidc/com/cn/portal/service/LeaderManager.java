package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Leader;
import aiidc.com.cn.portal.parameter.LeaderModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 业务接口
 */
public interface LeaderManager {
	
	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(LeaderModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Leader> ListQuery(LeaderModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Leader entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Leader entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Leader findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	public void delete(Leader entity);
}
