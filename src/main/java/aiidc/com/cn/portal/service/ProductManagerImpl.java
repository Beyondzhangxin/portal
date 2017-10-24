package aiidc.com.cn.portal.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aiidc.com.cn.portal.dao.ProductDao;
import aiidc.com.cn.portal.entity.Product;
import aiidc.com.cn.portal.parameter.ProductModel;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 企业文化与资质业务实现
 */
@Service("ProductManager")
public class ProductManagerImpl implements ProductManager {
	@Autowired
	private ProductDao productDao;

	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(Product entity) {
		entity.setCreateTime(Calendar.getInstance());
		productDao.persist(entity);
	}

	public void update(Product entity) {
		productDao.update(entity);
	}

	public Product findById(String entityId) {
		return productDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		productDao.deleteById(entityId);
	}

	public Pagination pageQuery(ProductModel queryModel) {
		return productDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<Product> ListQuery(ProductModel queryModel) {
		return productDao.listQuery(queryModel);
	}

	public List<Product> getAll() {
		return productDao.getAll();
	}

	public Product findByProperty(String Property, Object value) {
		return productDao.findByProperty(Property, value);
	}
	
}
