package aiidc.com.cn.portal.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

import aiidc.com.cn.portal.entity.AclUser;
/**
 * Date: 2017/9/6
 * Time: 9:40
 * @author fancw
 */
public class LoginHelper {

	protected static Logger logger = LoggerFactory.getLogger(LoginHelper.class);
	private static String LOGIN_USER ="loginUser";
	
	public static void setSession(HttpServletRequest request, AclUser entity) {
		Assert.notNull(entity);
		String ip = request.getRemoteHost();
		if ("0:0:0:0:0:0:0:1".equals(ip))
			ip = "127.0.0.1";
		WebUtils.setSessionAttribute(request, LOGIN_USER, entity);
		logger.debug("Set session attributes after login:{}", entity);
	}

	public static AclUser getLoginUser(HttpServletRequest request) {
		AclUser loginUser = (AclUser) WebUtils.getSessionAttribute(request, LOGIN_USER);
		return loginUser == null ? null : loginUser;
	}

}
