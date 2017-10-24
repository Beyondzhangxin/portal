package aiidc.com.cn.portal.service;

import aiidc.com.cn.portal.dao.OverviewDao;
import aiidc.com.cn.portal.entity.Overview;
import aiidc.com.cn.portal.parameter.OverviewModel;
import aiidc.com.cn.portal.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Zhangx on 2017/9/21 at 10:42.
 */
@Service("OverviewManager")
public class OverviewManagerImpl implements OverviewManager
{
    @Autowired
    private OverviewDao overviewDao;

    //@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)//业务层中加事务控制(如有需要)
    public void add(Overview entity)
    {
        overviewDao.persist(entity);
    }

    public void update(Overview entity)
    {
        overviewDao.update(entity);
    }

    public Overview findById(String entityId)
    {
        return overviewDao.findById(entityId);
    }

    public void deleteById(String entityId)
    {
        overviewDao.deleteById(entityId);
    }

    public Pagination pageQuery(OverviewModel queryModel)
    {
        return overviewDao.pageQuery(queryModel, queryModel.getPageNo(), queryModel.getPageSize());
    }

    public List<Overview> ListQuery(OverviewModel queryModel)
    {
        return overviewDao.listQuery(queryModel);
    }

    public List<Overview> getAll()
    {
        return overviewDao.getAll();
    }

    public Overview findByProperty(String Property, Object value)
    {
        return overviewDao.findByProperty(Property, value);
    }
}
