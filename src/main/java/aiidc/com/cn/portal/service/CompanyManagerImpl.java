package aiidc.com.cn.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.CompanyDao;
import aiidc.com.cn.portal.entity.Company;
import aiidc.com.cn.portal.parameter.CompanyModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 公司业务实现
 */
@Service("companyManager")
public class CompanyManagerImpl implements CompanyManager {
	@Autowired
	private CompanyDao companyDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Company entity) {
		companyDao.persist(entity);
	}

	public void update(Company entity) {
		companyDao.update(entity);
	}

	public Company findById(String entityId) {
		return companyDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		companyDao.deleteById(entityId);
	}

	public Pagination pageQuery(CompanyModel queryModel) {
		return companyDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Company> ListQuery(CompanyModel queryModel) {
		return companyDao.listQuery(queryModel);
	}
	
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	public Company findByProperty(String Property, Object value) {
		return companyDao.findByProperty(Property, value);
	}
}
