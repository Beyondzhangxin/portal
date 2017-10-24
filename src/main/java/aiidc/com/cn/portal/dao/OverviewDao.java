package aiidc.com.cn.portal.dao;

import aiidc.com.cn.portal.entity.Overview;
import aiidc.com.cn.portal.parameter.OverviewModel;
import aiidc.com.cn.portal.util.AbstractDao;
import aiidc.com.cn.portal.util.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangx on 2017/9/21 at 10:44.
 */
@Repository
public interface OverviewDao extends AbstractDao<Overview>
{
    /**
     * 根据条件列表查询数据
     *
     * @param queryModel
     * @return
     */
    public List<Overview> listQuery(OverviewModel queryModel);

    /**
     * 根据条件分页查询数据
     *
     * @param queryModel
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination pageQuery(OverviewModel queryModel, int pageNo, int pageSize);
}
