package aiidc.com.cn.portal.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.LeaderDao;
import aiidc.com.cn.portal.entity.Leader;
import aiidc.com.cn.portal.parameter.LeaderModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 业务实现
 */
@Service("LeaderManager")
public class LeaderManagerImpl implements LeaderManager {
	@Autowired
	private LeaderDao leaderDao;


	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Leader entity) {
		entity.setCreateTime(Calendar.getInstance());
		leaderDao.persist(entity);
	}

	public void update(Leader entity) {
		leaderDao.update(entity);
	}

	public Leader findById(String entityId) {
		return leaderDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		leaderDao.deleteById(entityId);
	}

	public Pagination pageQuery(LeaderModel queryModel) {
		return leaderDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Leader> ListQuery(LeaderModel queryModel) {
		return leaderDao.listQuery(queryModel);
	}

	public void delete(Leader entity) {
		leaderDao.delete(entity);
	}
	
}
