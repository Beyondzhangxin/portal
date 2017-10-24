package aiidc.com.cn.portal.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
/**
 * /**
 * Date: 2017/9/5
 * Time: 10:19
 * @author fancw
 */
@SuppressWarnings("deprecation")
@MappedSuperclass
public abstract class PaginationToJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2313696048038752791L;
	public static final String FILTER_NAME = "myFilter";
	protected static ObjectMapper objectMapper;
	static {
		objectMapper = new ObjectMapper();
		// 设置日期解析格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// enum用toString方法
		objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		// 属性不存在时不报错
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		// 默认filter
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(FILTER_NAME,
				SimpleBeanPropertyFilter.serializeAllExcept(""));
		objectMapper.setFilters(filterProvider);
	}

	public String toString() {
		return toJson();
	}

	public String toJson() {
		try {
			return objectMapper.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
