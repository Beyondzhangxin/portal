package aiidc.com.cn.portal.dao.jpa;

import aiidc.com.cn.portal.dao.OverviewDao;
import aiidc.com.cn.portal.entity.Overview;
import aiidc.com.cn.portal.parameter.OverviewModel;
import aiidc.com.cn.portal.util.AbstractDaoJpa;
import aiidc.com.cn.portal.util.Pagination;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Zhangx on 2017/9/21 at 10:46.
 */
@Repository("OverviewDao")
public class OverviewDaoJpa extends AbstractDaoJpa<Overview> implements OverviewDao
{
    public List<Overview> listQuery(OverviewModel queryModel)
    {
        param = DetachedCriteria.forClass(clazz);
        setQueryParameters(param, queryModel);
        return super.queryList(param);
    }

    public Pagination pageQuery(OverviewModel queryModel, int pageNo, int pageSize)
    {
        param = DetachedCriteria.forClass(clazz);
        setQueryParameters(param, queryModel);
        return super.queryPage(param, pageNo, pageSize);
    }

    /**
     * 设置param查询参数
     *
     * @param param
     * @param queryModel
     */
    private void setQueryParameters(DetachedCriteria param, OverviewModel queryModel)
    {
        if (StringUtils.hasText(queryModel.getType()))
        {
            param.add(Restrictions.eq("type", queryModel.getType()));
        }
    }
}
