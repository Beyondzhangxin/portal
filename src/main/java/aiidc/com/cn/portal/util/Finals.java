package aiidc.com.cn.portal.util;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Finals {

	/* 审核状态(0:新增(设置默认值) 1:审核通过  2:未通过) */
	public static final String STATUS_DEFAULT = "0";
	public static final String STATUS_YES = "1";
	public static final String STATUS_NO = "2";
	
	public static final String COMPANY_TYPE_CULTURE ="1"; //企业文化
	public static final String COMPANY_TYPE_HONOR ="2";   //荣誉与资质
	
	/**
	 * 图片上传type  对应Pic这张表
	 */
	public static final String PIC_TYPE_ORGANIZE = "organize";  //组织机构使用
	public static final String PIC_TYPE_LEADER = "leader";  //领导管理使用
	public static final String PIC_TYPE_NEWS = "news";    //新闻管理使用
	public static final String PIC_TYPE_SOLUTION = "solution";    //解决方案使用
	public static final String PIC_TYPE_CULTURE = "culture";//企业文化使用
	public static final String PIC_TYPE_HONOR = "honor";
	public static final String PIC_TYPE_PERSON = "person";    //用户头像使用
	public static final String PIC_TYPE_NEW_CONTENT = "newscontent";    //新闻内容使用
	public static final String PIC_TYPE_PRODUCTS = "products";    //公司产品使用


	/**
	 * 解决方案类型设置
	 */
	public static final String SOLUTION_TYPE_GEN = "1"; //总体解决方案
	public static final String SOLUTION_TYPE_SPEC = "2"; //专项解决方案

	/**
	 * 公司首页总述的类型
	 */
	public static final String OVERVIEW_TYPE_SOLUTION = "1";
	public static final String OVERVIEW_TYPE_PRODUCT = "2";
	
	//产品类型
	public static final Map<String,String> PRODUCT_TYPE= new LinkedHashMap<String, String>();
	static {
		PRODUCT_TYPE.put("1", "平台类");
		PRODUCT_TYPE.put("2", "行业类");
	}
	
}
