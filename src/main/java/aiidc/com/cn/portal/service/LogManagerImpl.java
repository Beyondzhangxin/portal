package aiidc.com.cn.portal.service;

import aiidc.com.cn.portal.dao.LogDao;
import aiidc.com.cn.portal.entity.Log;
import aiidc.com.cn.portal.parameter.LogModel;
import aiidc.com.cn.portal.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司新闻业务实现
 */
@Service("logManager")
public class LogManagerImpl implements LogManager {
	@Autowired
	private LogDao logDao;


	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Log entity) {
		logDao.persist(entity);
	}

	public void update(Log entity) {
		logDao.update(entity);
	}

	public Log findById(String entityId) {
		return logDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		logDao.deleteById(entityId);
	}

	public Pagination pageQuery(LogModel queryModel) {
		return logDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Log> ListQuery(LogModel queryModel) {
		return logDao.listQuery(queryModel);
	}

	public List<Log> getAll() {
		return logDao.getAll();
	}

	public Log findByProperty(String Property, Object value) {
		return logDao.findByProperty(Property, value);
	}
	
}
