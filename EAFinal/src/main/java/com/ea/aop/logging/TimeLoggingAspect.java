package com.ea.aop.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by amanadhikari on 9/27/16.
 */
@Component
@Aspect
public class TimeLoggingAspect {
    @Before("execution(* com.ea.service.*.*(..))")
    public void logBefore(){
        System.out.println("@Before:"+System.currentTimeMillis() +" ms");
    }

    @After("execution(* com.ea.service.*.*(..))")
    public void logAfter(){
        System.out.println("@After:"+System.currentTimeMillis()+" ms");
    }
}
