package aiidc.com.cn.portal.dao;

import java.util.List;

import aiidc.com.cn.portal.entity.News;
import aiidc.com.cn.portal.parameter.NewsModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;

/**
 * 公司新闻Dao接口
 */
public interface NewsDao extends AbstractDao<News> {
	/**
	 * 根据条件列表查询数据
	 * @param queryModel
	 * @return
	 */
	public List<News> listQuery(NewsModel queryModel);
	
	/**
	 * 根据条件分页查询数据
	 * @param queryModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination pageQuery(NewsModel queryModel, int pageNo, int pageSize);
}
