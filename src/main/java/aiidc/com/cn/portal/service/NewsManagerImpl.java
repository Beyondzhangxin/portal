package aiidc.com.cn.portal.service;

import aiidc.com.cn.portal.dao.NewsDao;
import aiidc.com.cn.portal.entity.News;
import aiidc.com.cn.portal.parameter.NewsModel;
import aiidc.com.cn.portal.util.Pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司新闻业务实现
 */
@Service("newsManager")
public class NewsManagerImpl implements NewsManager {
	@Autowired
	private NewsDao newsDao;


	//@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
	public void add(News entity) {
		newsDao.persist(entity);
	}

	public void update(News entity) {
		newsDao.update(entity);
	}

	public News findById(String entityId) {
		return newsDao.findById(entityId);
	}

	public void deleteById(String entityId) {
		newsDao.deleteById(entityId);
	}

	public Pagination pageQuery(NewsModel queryModel) {
		return newsDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
	}

	public List<News> ListQuery(NewsModel queryModel) {
		return newsDao.listQuery(queryModel);
	}

	public List<News> getAll() {
		return newsDao.getAll();
	}

	public News findByProperty(String Property, Object value) {
		return newsDao.findByProperty(Property, value);
	}
	
}
