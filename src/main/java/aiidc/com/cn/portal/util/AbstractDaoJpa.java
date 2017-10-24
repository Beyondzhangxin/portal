package aiidc.com.cn.portal.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public class AbstractDaoJpa<E extends BaseEntity> implements AbstractDao<E> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected HibernateTemplate hibernateTemplate;
	protected final Class<E> clazz;
	protected DetachedCriteria param;

	@SuppressWarnings("unchecked")
	protected AbstractDaoJpa() {
		clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//		param = DetachedCriteria.forClass(clazz);
		Assert.notNull(clazz, "无法获得泛型的class");
		
	}

	protected AbstractDaoJpa(Class<E> clazz) {
		this.clazz = clazz;
	}

	public Class<E> getClazz() {
		return clazz;
	}

	@Transactional(readOnly = false)
	public void persist(E entity) {
		hibernateTemplate.save(entity);
	}

	public E findById(Serializable id) {
		return (E) (id == null ? null : hibernateTemplate.get(clazz, id));
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> queryList(DetachedCriteria param) {
		return (List<T>) hibernateTemplate.findByCriteria(param);
	}
    /**
     * 分页查询
     *
     * @param dc       查询条件
     * @param page     当前页
     * @param pageSize 每页显示的记录数
     * @param clazz
     * @return
     */
    public <T> Pagination queryPage(DetachedCriteria param, int pageNo, int pageSize)
    {
        int startIndex = (pageNo - 1) * pageSize;
        int size = this.queryList(param).size();
        List<?> list = hibernateTemplate.findByCriteria(param, startIndex, pageSize);
        Pagination pagn = new Pagination();
        pagn.setResult(list);
        pagn.setCurrentPage(pageNo);
        pagn.setPageSize(pageSize);
        pagn.setPageCount(list.size());
        pagn.setTotalCount(size);
        return pagn;
    }
	
	

	public void saveOrUpdate(E obj) {
		hibernateTemplate.saveOrUpdate(obj);
	}

	@Transactional(readOnly = false)
	public void update(E obj) {
		hibernateTemplate.update(obj);
	}

	@Transactional(readOnly = false)
	public void delete(E obj) {
		hibernateTemplate.delete(obj);
	}
	
	@Transactional(readOnly = false)
	public int deleteById(Serializable id) {
		E e = this.findById(id);
		if (e == null) return 0;
		this.delete(e);
		return 1;
	}

	@Transactional(readOnly = false)
	public void deleteAll(Collection<E> collections) {
		hibernateTemplate.deleteAll(collections);
	}

	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		param = DetachedCriteria.forClass(clazz);
		return (List<E>) hibernateTemplate.findByCriteria(param);
	}


	public void saveOrUpdateAll(Collection<E> entities) {
		if (entities == null)
			return;
		for (E entity : entities) {
			hibernateTemplate.saveOrUpdate(entity);
		}
	}

	@SuppressWarnings("unchecked")
	public E findByProperty(String propertyName, Object value) {
		param = DetachedCriteria.forClass(clazz);
		param.add(Restrictions.eq(propertyName, value));
		List<Object> list = this.queryList(param);
		if(list == null || list.size()<=0){
			return null;
		}else{
			return (E) list.get(0);
		}
	}

}