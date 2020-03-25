package com.douzone.jblog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {

	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		StopWatch sw = new StopWatch();		
		sw.start();
		
		// before
		Object result = pjp.proceed();
		
		// after
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
	
		String className = pjp.getTarget().getClass().getName();
		String methodName= pjp.getSignature().getName();
		String task = className + "." + methodName;
		System.out.println("[Execution Time][" + task + "] " + totalTime + "millis");
		
		return result;
	}
}
