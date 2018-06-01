package com.zzx.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggerAspect {

	/**
	 * 定义切入点
	 */
    @Pointcut("execution(public * com.zzx.controller.*.*(..))")  
    public void webLog(){}  
    
    
    @Before("webLog()")  
    public void deBefore(JoinPoint joinPoint) throws Throwable {  
        // 接收到请求，记录请求内容  
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
        HttpServletRequest request = attributes.getRequest();  
        // 记录下请求内容  
        System.out.println("URL : " + request.getRequestURL().toString());  
        System.out.println("HTTP_METHOD : " + request.getMethod());  
        System.out.println("IP : " + request.getRemoteAddr());  
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());  
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));  
  
    }  
    
    
    @AfterReturning(returning = "ret", pointcut = "webLog()")  
    public void doAfterReturning(Object ret) throws Throwable {  
        // 处理完请求，返回内容  
        System.out.println("方法的返回值 : " + ret);  
    }  
  
    //后置异常通知  
    @AfterThrowing(throwing = "ex", pointcut ="execution(public * com.zzx.controller.*.*(..))")  
    public void throwss(JoinPoint jp,Throwable ex){  
        System.out.println("方法异常时执行.....");  
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("webLog()")  
    public void after(JoinPoint jp){  
        System.out.println("方法最后执行.....");  
    }  
    
    //环绕通知,环绕增强，相当于MethodInterceptor  
    //该环绕通知将异常处理了 故后置异常通知@AfterThrowing不会执行
    @Around("webLog()")  
    public Object arround(ProceedingJoinPoint pjp) {  
        System.out.println("方法环绕start.....");  
        try {  
            Object o =  pjp.proceed();  
            System.out.println("方法环绕proceed，结果是 :" + o);  
            return o;  
        } catch (Throwable e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
}
