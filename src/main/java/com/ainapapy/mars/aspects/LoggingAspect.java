package com.ainapapy.mars.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Aspect pour logging
 * @author ainap
 */

@Aspect
@Component
public class LoggingAspect {
    
    @Before("execution(* com.ainapapy.mars.*.*(..))")
    public void logBeforeMethodeExecution() {
        System.out.println(" Methode appelée ...");
    }
}
