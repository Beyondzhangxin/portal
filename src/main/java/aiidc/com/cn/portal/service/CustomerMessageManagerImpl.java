package aiidc.com.cn.portal.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.CustomerMessageDao;
import aiidc.com.cn.portal.entity.CustomerMessage;
import aiidc.com.cn.portal.parameter.CustomerMessageModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 客户留言业务实现
 */
@Service("customerMessageManager")
public class CustomerMessageManagerImpl implements CustomerMessageManager {
	@Autowired
	private CustomerMessageDao customerMessageDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(CustomerMessage entity) {
		entity.setCreateTime(Calendar.getInstance());
		customerMessageDao.persist(entity);
	}

	public void update(CustomerMessage entity) {
		customerMessageDao.update(entity);
	}

	public CustomerMessage findById(String entityId) {
		return customerMessageDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		customerMessageDao.deleteById(entityId);
	}

	public Pagination pageQuery(CustomerMessageModel queryModel) {
		return customerMessageDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<CustomerMessage> ListQuery(CustomerMessageModel queryModel) {
		return customerMessageDao.listQuery(queryModel);
	}

	public List<CustomerMessage> getAll() {
		return customerMessageDao.getAll();
	}

	public CustomerMessage findByProperty(String Property, Object value) {
		return customerMessageDao.findByProperty(Property, value);
	}
	
}
