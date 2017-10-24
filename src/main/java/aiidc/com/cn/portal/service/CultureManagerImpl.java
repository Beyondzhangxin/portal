package aiidc.com.cn.portal.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.CultureDao;
import aiidc.com.cn.portal.entity.Culture;
import aiidc.com.cn.portal.parameter.CultureModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质业务实现
 */
@Service("cultureManager")
public class CultureManagerImpl implements CultureManager {
	@Autowired
	private CultureDao cultureDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Culture entity) {
		entity.setCreateTime(Calendar.getInstance());
		cultureDao.persist(entity);
	}

	public void update(Culture entity) {
		cultureDao.update(entity);
	}

	public Culture findById(String entityId) {
		return cultureDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		cultureDao.deleteById(entityId);
	}

	public Pagination pageQuery(CultureModel queryModel) {
		return cultureDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Culture> ListQuery(CultureModel queryModel) {
		return cultureDao.listQuery(queryModel);
	}

	public List<Culture> getAll() {
		return cultureDao.getAll();
	}

	public Culture findByProperty(String Property, Object value) {
		return cultureDao.findByProperty(Property, value);
	}
	
}
