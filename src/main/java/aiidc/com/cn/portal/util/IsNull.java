package aiidc.com.cn.portal.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

public class IsNull {
	protected static final boolean isEmpty(Map<?, ?> map) {
		return CollectionUtils.isEmpty(map);
	}

	protected static final boolean isEmpty(Object[] values) {
		return ObjectUtils.isEmpty(values);
	}
	protected static final boolean isEmpty(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}
}
