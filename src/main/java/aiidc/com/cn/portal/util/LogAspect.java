package aiidc.com.cn.portal.util;

import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.entity.Log;
import aiidc.com.cn.portal.service.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * 日志记录，添加、删除、修改、登录等操作记录
 * Created by Zhangx on 2017/10/17 at 14:02.
 */
@Aspect
@Component
public class LogAspect
{
    @Autowired
    private LogManager logManager;

    @Pointcut("execution(* aiidc.com.cn.portal.service.*Impl.add(..))&&!execution(* aiidc.com.cn.portal.service.LogManagerImpl.add(..))")
    public void insertServiceCall()
    {
    }

    @Pointcut("execution(* aiidc.com.cn.portal.service.*Impl.delete*(..))&&!execution(* aiidc.com.cn.portal.service.LogManagerImpl.deleteById(..))")
    public void deleteServiceCall()
    {
        System.out.println("is deleted!");
    }

    @Pointcut("execution(* aiidc.com.cn.portal.service.*Impl.update(..))&&!execution(* aiidc.com.cn.portal.service.LogManagerImpl.update(..))")
    public void updateServiceCall()
    {
    }
    @Pointcut("execution(* aiidc.com.cn.portal.controller.LoginController.welcome(..))")
    public void loginCall(){}

    @After("updateServiceCall()")
    public void afterUpdate(JoinPoint joinPoint) throws Throwable
    {
        String loginName = getLoginUserName();
        String ipAddress = PathUtil.getIPAddress();
        Calendar calendar = Calendar.getInstance();
        String className = joinPoint.getArgs()[0].getClass().getName();
        String name = className.substring(className.lastIndexOf(".") + 1);
        String event = "执行了一次"+name+"的更新操作！";
        Log log = new Log();
        log.setName(loginName);
        log.setIp(ipAddress);
        log.setTime(calendar);
        log.setEvent(event);
        logManager.add(log);
    }

    @After("insertServiceCall()")
    public void afterInsert(JoinPoint joinPoint) throws Throwable
    {
        String loginName = getLoginUserName();
        String ipAddress = PathUtil.getIPAddress();
        Calendar calendar = Calendar.getInstance();
        String className = joinPoint.getArgs()[0].getClass().getName();
        String name = className.substring(className.lastIndexOf(".") + 1);
        String event = "执行了一次" + name + "的添加操作！";
        Log log = new Log();
        log.setName(loginName);
        log.setIp(ipAddress);
        log.setTime(calendar);
        log.setEvent(event);
        logManager.add(log);
    }
    @After("deleteServiceCall()")
    public void afterDelete(JoinPoint joinPoint) throws Throwable
    {
        String loginName = getLoginUserName();
        String ipAddress = PathUtil.getIPAddress();
        Calendar calendar = Calendar.getInstance();
        String className = joinPoint.getArgs()[0].getClass().getName();
        String name = className.substring(className.lastIndexOf(".") + 1);
        String event = "执行了一次"+name+"的删除操作！";
        Log log = new Log();
        log.setName(loginName);
        log.setIp(ipAddress);
        log.setTime(calendar);
        log.setEvent(event);
        logManager.add(log);
    }


    @After("loginCall()")
    public void afterLogin(JoinPoint joinPoint) throws Throwable
    {
        String loginName = getLoginUserName();
        String ipAddress = PathUtil.getIPAddress();
        Calendar calendar = Calendar.getInstance();
        String event = "执行了一次登录操作！";
        Log log = new Log();
        log.setName(loginName);
        log.setIp(ipAddress);
        log.setTime(calendar);
        log.setEvent(event);
        logManager.add(log);
    }

    public String getLoginUserName(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AclUser loginUser = LoginHelper.getLoginUser(request);
        String loginName = loginUser.getLoginName();
        return loginName;
    }
}
