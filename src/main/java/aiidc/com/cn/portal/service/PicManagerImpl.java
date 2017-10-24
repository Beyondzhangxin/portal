package aiidc.com.cn.portal.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.PicDao;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.PicModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质业务实现
 */
@Service("PicManager")
public class PicManagerImpl implements PicManager {
	@Autowired
	private PicDao picDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Pic entity) {
		entity.setCreateTime(Calendar.getInstance());
		picDao.persist(entity);
	}

	public void update(Pic entity) {
		entity.setCreateTime(Calendar.getInstance());
		picDao.update(entity);
	}

	public Pic findById(String entityId) {
		return picDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		picDao.deleteById(entityId);
	}

	public Pagination pageQuery(PicModel queryModel) {
		return picDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Pic> ListQuery(PicModel queryModel) {
		return picDao.listQuery(queryModel);
	}

	public List<Pic> getAll() {
		return picDao.getAll();
	}

	public Pic findByProperty(String Property, Object value) {
		return picDao.findByProperty(Property, value);
	}
	
}
