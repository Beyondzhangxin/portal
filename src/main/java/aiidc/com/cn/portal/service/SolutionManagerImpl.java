package aiidc.com.cn.portal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.SolutionDao;
import aiidc.com.cn.portal.entity.Solution;
import aiidc.com.cn.portal.parameter.SolutionModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 解决方案业务实现
 */
@Service("solutionManager")
public class SolutionManagerImpl implements SolutionManager {
	@Autowired
	private SolutionDao solutionDao;
	
	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Solution entity) {
		solutionDao.persist(entity);
	}

	public void update(Solution entity) {
		solutionDao.update(entity);
	}

	public Solution findById(String entityId) {
		return solutionDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		solutionDao.deleteById(entityId);
	}

	public Pagination pageQuery(SolutionModel queryModel) {
		return solutionDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Solution> ListQuery(SolutionModel queryModel) {
		return solutionDao.listQuery(queryModel);
	}

	public List<Solution> getAll() {
		return solutionDao.getAll();
	}

	public Solution findByProperty(String Property, Object value) {
		return solutionDao.findByProperty(Property, value);
	}
	
}
