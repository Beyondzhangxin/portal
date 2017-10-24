package aiidc.com.cn.portal.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.MapType;

@SuppressWarnings("deprecation")
public abstract class JsonTools {

	public static final String FILTER_NAME = "myFilter";
	protected static Logger logger = LoggerFactory.getLogger(JsonTools.class);
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

	/**
	 * 转换成json字符串，可定义忽略的属性。<BR>
	 * 已经使用{@link JsonIgnore}标注的不用指定也会忽略。<BR>
	 * 被转换的entity需要在类上加{@link JsonFilter}注解
	 * 
	 * @param entity
	 * @param ingoreProperties
	 * @return
	 * @throws IOException
	 */
	public final static String toJsonStr(BaseEntity entity, String... ingoreProperties) throws IOException {
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(FILTER_NAME,
				SimpleBeanPropertyFilter.serializeAllExcept(ingoreProperties));
		return objectMapper.writer(filterProvider).writeValueAsString(entity);
	}

	/**
	 * 将entity对象转换成json字符串
	 * 
	 * @param entity
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String toJsonStr(BaseEntity entity) throws IOException {
		return objectMapper.writeValueAsString(entity);
	}

	/**
	 * 将集合对象转换成json字符串
	 * 
	 * @param <T>
	 *            集合内的对象类型必须继承entity
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static <T extends BaseEntity> String toJsonStr(Collection<T> collection) throws IOException {
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将Map转换成Json字符串
	 * 
	 * @param map
	 *            map
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String toJsonStr(Map<?, ?> map) throws IOException {
		return objectMapper.writeValueAsString(map);
	}

	/**
	 * 将集合对象转换成json字符串(尽量使用entity格式对象)
	 * 
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String strArray2JsonStr(Collection<String> collection) throws IOException {
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将集合对象转换成json字符串(尽量使用entity格式对象)
	 * 
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static String boolArray2JsonStr(Collection<Boolean> collection) throws IOException {
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将集合对象转换成json字符串(尽量使用entity格式对象)
	 * 
	 * @param collection
	 *            集合
	 * @return json格式字符串
	 * @throws IOException
	 */
	public final static <T extends Number> String numberArray2JsonStr(Collection<T> collection) throws IOException {
		return objectMapper.writeValueAsString(collection);
	}

	/**
	 * 将json字符串转换成entity对象
	 * 
	 * @param <T>
	 *            对象类型
	 * @param jsonStr
	 *            json字符串
	 * @param clazz
	 *            转换后的对象类型，必须是继承BaseEntity的对象
	 * @return 转换后的对象
	 * @throws IOException
	 */
	public final static <T extends BaseEntity> T json2Object(String jsonStr, Class<T> clazz) throws IOException {
		if (!StringUtils.hasText(jsonStr))
			return null;
		return objectMapper.readValue(jsonStr, clazz);
	}

	/**
	 * 将json字符串转换成集合对象(尽量使用继承BaseEntity的对象)
	 * 
	 * @param <E>
	 *            对象类型
	 * @param jsonStr
	 *            json字符串
	 * @param eleClazz
	 *            转换后的对象类型，必须是继承BaseEntity的对象
	 * @return 转换后的集合
	 * @throws IOException
	 */
	public final static <E> List<E> json2List(String jsonStr, Class<E> eleClazz) throws IOException {
		if (!StringUtils.hasText(jsonStr) || eleClazz == null)
			return new ArrayList<E>();
		JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, eleClazz);
		return objectMapper.readValue(jsonStr, type);
	}

	/**
	 * json字符串转换成Map对象
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @param keyClass
	 *            key的对象类型
	 * @param valueClass
	 *            value的对象类型
	 * @return 转换后的Map
	 * @throws IOException
	 */
	public final static <K, V> Map<K, V> json2Map(String jsonStr, Class<K> keyClass, Class<V> valueClass)
			throws IOException {
		if (!StringUtils.hasText(jsonStr) || keyClass == null || valueClass == null)
			return new HashMap<K, V>();
		MapType type = objectMapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
		return objectMapper.readValue(jsonStr, type);
	}
	
	
	public static void writeJson(HttpServletResponse response, Object obj) throws IOException {
		response.setHeader("ContentType", "text/json");
		response.setHeader("Content-Type", "text/json;charset=urf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(obj);
		out.close();
	}
}
