package aiidc.com.cn.portal.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface AbstractDao<E extends BaseEntity> {
	/**
	 * 保存一个实例
	 * 
	 * @param obj
	 * @return
	 */
	public void persist(E entity);

	/**
	 * 根据ID查找记录
	 * 
	 * @param id
	 * @return
	 */
	public E findById(Serializable id);

	/**
	 * 查找指定属性的记录(属性值唯一不可重复)
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public E findByProperty(String propertyName,Object value);

	/**
	 * 保存或更新该条记录
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(E obj);

	/**
	 * 批量更新记录
	 * 
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<E> entities);

	/**
	 * 更新该条记录
	 * 
	 * @param obj
	 */
	public void update(E obj);

	/**
	 * 删除该实例
	 * 
	 * @param obj
	 */
	public void delete(E obj);

	/**
	 * 删除指定ID的记录
	 * 
	 * @param id
	 *            id的值
	 * @return 删除的记录数：记录存在返回1，否则返回0
	 */
	public int deleteById(Serializable id);

	/**
	 * 删除一组实例
	 * 
	 * @param collections
	 *            存放实例的collections
	 */
	public void deleteAll(Collection<E> collections);

	/**
	 * 获取所有记录
	 * 
	 * @return
	 * @see {@link #getAllInOrder(String)}
	 */
	public List<E> getAll();

}