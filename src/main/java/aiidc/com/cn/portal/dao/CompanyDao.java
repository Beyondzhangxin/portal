package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.Company;
import aiidc.com.cn.portal.parameter.CompanyModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 公司Dao接口
 */
public interface CompanyDao extends AbstractDao<Company> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<Company> listQuery(CompanyModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(CompanyModel queryModel, int pageNo, int pageSize);
}
