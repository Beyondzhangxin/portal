package aiidc.com.cn.portal.util;

public class OrderPaginModel {
	
	private int pageNo = SysFinals.DEFAULT_PAGE_NUM;
	private int pageSize = SysFinals.DEFAULT_PAGE_SIZE;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
