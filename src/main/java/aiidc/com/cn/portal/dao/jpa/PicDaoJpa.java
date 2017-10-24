package aiidc.com.cn.portal.dao.jpa;

import aiidc.com.cn.portal.dao.PicDao;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.PicModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 图片Dao Jpa实现
 */
@Repository("PicDao")
public class PicDaoJpa extends AbstractDaoJpa<Pic> implements PicDao {

	public List<Pic> listQuery(PicModel queryModel) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		param.addOrder(Order.asc("list"));
		return super.queryList(param);
	}

	public Pagination pageQuery(PicModel queryModel, int pageNo, int pageSize) {
		param = DetachedCriteria.forClass(clazz);
		setQueryParameters(param, queryModel);
		return super.queryPage(param, pageNo, pageSize);
	}
	
	/**
	 * 设置param查询参数
	 * @param param
	 * @param queryModel
	 */
	private void setQueryParameters(DetachedCriteria param, PicModel queryModel) {
//		if (StringUtils.hasText(queryModel.getLoginName())) {
//			param.add(Restrictions.eq("loginName", queryModel.getLoginName()));
//		}
		if(StringUtils.hasText(queryModel.getItemId())){
			param.add(Restrictions.eq("itemId", queryModel.getItemId()));
		}
	}

}
