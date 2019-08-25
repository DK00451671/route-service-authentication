package com.route.authentication.routeserviceauthentication.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectBO {

    @Pointcut("execution(* com.route.authentication.routeserviceauthentication.service.*.*(..))")
    public void myPointCut() {

    }

    @Before("myPointCut()")
    public void before() {
        System.out.println(" Check for access called 1111.......");
       // System.out.println(joinPoint.getSignature().getName() + " Aspect is called");
    }


    @Before("myPointCut()")
    public void beforetwo() {
        System.out.println(" Check for access called 2222.......");
        // System.out.println(joinPoint.getSignature().getName() + " Aspect is called");
    }

} //end of class AspectBO()
