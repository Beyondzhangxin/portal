package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.Leader;
import aiidc.com.cn.portal.parameter.LeaderModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Dao接口
 */
public interface LeaderDao extends AbstractDao<Leader> {
	
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<Leader> listQuery(LeaderModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(LeaderModel queryModel, int pageNo, int pageSize);
	
}
