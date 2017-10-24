package aiidc.com.cn.portal.service;

import java.util.List;

import aiidc.com.cn.portal.entity.Recruitment;
import aiidc.com.cn.portal.parameter.RecruitmentModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 人才招聘业务接口
 */
public interface RecruitmentManager {

	/**
	 * 根据条件分页查询
	 * @param queryModel
	 * @return
	 */
	public Pagination pageQuery(RecruitmentModel queryModel);
	/**
	 * 根据条件查询所有list集合
	 * @param queryModel
	 * @return
	 */
	public List<Recruitment> ListQuery(RecruitmentModel queryModel);
	/**
	 * 添加一条记录
	 * @param entity
	 */
	public void add(Recruitment entity);
	
	/**
	 * 修改一条记录
	 * @param entity
	 */
	public void update(Recruitment entity);
	
	/**
	 * 根据ID查找一条记录
	 * @param entityId
	 * @return
	 */
	public Recruitment findById(String entityId);
	
	/**
	 * 根据ID删除一条记录
	 * @param entityId
	 */
	public void deleteById(String entityId);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Recruitment> getAll();
	
	/**
	 * 根据属性值查询实体(属性值唯一，不重复)
	 * @param Property
	 * @param value
	 * @return
	 */
	public Recruitment findByProperty(String Property , Object value);
}
