package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.Solution;
import aiidc.com.cn.portal.parameter.SolutionModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 解决方案Dao接口
 */
public interface SolutionDao extends AbstractDao<Solution> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<Solution> listQuery(SolutionModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(SolutionModel queryModel, int pageNo, int pageSize);
}
