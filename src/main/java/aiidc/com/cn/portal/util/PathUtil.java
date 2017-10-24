package aiidc.com.cn.portal.util;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class PathUtil
{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(PathUtil.class);

    /**
     * 获取应用的根路径
     *
     * @return
     */
    public static String getRootPath()
    {
        logger.info("==============================进入【获取应用的根路径方法】========================================");
        //获取request
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取工程应用的跟路径
//		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
        //获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前应用在服务器的目录
        String path = request.getSession().getServletContext().getRealPath(File.separator);
        //获取应用
        int lastIndexOf = path.lastIndexOf("webapps");
        String servicePath = path.substring(0, lastIndexOf + 7);
        if (lastIndexOf < 0)
        {
            servicePath = path;
        }
        logger.info("tomcat-webapp的绝对路径：{}", servicePath);
        return servicePath;
    }

    /**
     * 获取IP地址
     *
     * @return
     */
    public static String getIPAddress()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip))
            return "127.0.0.1";
        return ip;
    }

    public String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();

        }
        return ip;

    }


}
