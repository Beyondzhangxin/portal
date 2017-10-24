package aiidc.com.cn.portal.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Date: 2017/9/5
 * Time: 11:19
 * @author fancw
 */
public class Pagination extends PaginationToJson{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5252422656890676373L;
	private int currentPage;  //当前页
	private int pageSize = SysFinals.DEFAULT_PAGE_SIZE;
	private long pageCount;  //当前页的记录数
	private long totalCount; //总记录数
	private List<?> result;  //分页查询当前页list

	public Pagination() {

	}

	public Pagination(int curretPage, int pageSize) {
		this.currentPage = curretPage;
		this.pageSize = pageSize;
	}

	public Pagination(int currentPage, int pageSize, long totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	/**
	 * 设置完成totalcount和pagesize以后调用此方法自动计算其他值
	 * 
	 * @return
	 */
	public Pagination compute() {
		if (totalCount < 0 || pageSize <= 0)
			throw new RuntimeException("totalCount和pageSize设置之前不能进行计算");
		if (currentPage <= 0)
			currentPage = 1;// 当前页不能为负数
		pageCount = getPageCount(totalCount, pageSize);
		int count = Long.valueOf(pageCount).intValue();
		if (currentPage > count)
			currentPage = count;// 如页码过大，返回最后一页
		return this;
	}

	public Pagination(List<?> result) {
		this.result = result;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public Pagination setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		return this;
	}

	public long getPageCount() {
		return pageCount;
	}

	public Pagination setPageCount(long pageCount) {
		this.pageCount = pageCount;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Pagination setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public List<?> getResult() {
		return result;
	}

	public Pagination setResult(List<?> result) {
		this.result = result;
		return this;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public Pagination setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getResult(Class<T> clazz) {
		return (List<T>) result;
	}

	public static long getPageCount(long totalCount, int pageSize) {
		if (totalCount < 0 || pageSize <= 0)
			return 0;
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	@SuppressWarnings("unchecked")
	public <F, T> Pagination convertResult(ResultConverter<F, T> converter) {
		List<T> rs = new ArrayList<T>();
		for (Object obj : result) {
			rs.add(converter.convert((F) obj));
		}
		result = rs;
		return this;
	}

	public static Pagination emptyInstance() {
		return new Pagination().compute().setResult(new ArrayList<Object>());
	}


	public interface ResultConverter<F, T> {
		public T convert(F from);
	}
//	@Override
//	public String toString() {
//		return "Pagination [currentPage=" + currentPage + ", pageCount=" + pageCount + ", pageSize=" + pageSize
//				+ ", result.size()=" + result.size() + ", totalCount=" + totalCount + "]";
//	}
	
	public final String toJson() {
		try {
			return objectMapper.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
