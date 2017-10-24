package aiidc.com.cn.portal.dao;

import aiidc.com.cn.portal.entity.Log;
import aiidc.com.cn.portal.parameter.LogModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

import java.util.List;

/**
 * 图片Dao接口
 */
public interface LogDao extends AbstractDao<Log> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<Log> listQuery(LogModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(LogModel queryModel, int pageNo, int pageSize);
}
