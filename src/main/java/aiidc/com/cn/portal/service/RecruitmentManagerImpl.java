package aiidc.com.cn.portal.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.RecruitmentDao;
import aiidc.com.cn.portal.entity.Recruitment;
import aiidc.com.cn.portal.parameter.RecruitmentModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 人才招聘业务实现
 */
@Service("recruitmentManager")
public class RecruitmentManagerImpl implements RecruitmentManager {
	@Autowired
	private RecruitmentDao recruitmentDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Recruitment entity) {
		entity.setCreateTime(Calendar.getInstance());
		recruitmentDao.persist(entity);
	}

	public void update(Recruitment entity) {
		recruitmentDao.update(entity);
	}

	public Recruitment findById(String entityId) {
		return recruitmentDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		recruitmentDao.deleteById(entityId);
	}

	public Pagination pageQuery(RecruitmentModel queryModel) {
		return recruitmentDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Recruitment> ListQuery(RecruitmentModel queryModel) {
		return recruitmentDao.listQuery(queryModel);
	}

	public List<Recruitment> getAll() {
		return recruitmentDao.getAll();
	}

	public Recruitment findByProperty(String Property, Object value) {
		return recruitmentDao.findByProperty(Property, value);
	}
	
}
