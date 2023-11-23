package com.babinska.plannerfortutor.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAdvice {

  @Pointcut("execution(public * *(..))")
  public void publicMethod() {
  }

  @Pointcut("within(@com.babinska.plannerfortutor.aspect.TrackExecutionTime *)")
  public void typeAnnotatedWithTrackExecutionTime() {
  }

  @Pointcut("publicMethod() && typeAnnotatedWithTrackExecutionTime()")
  public void typeAnnotated() {
  }

  @Pointcut("@annotation(com.babinska.plannerfortutor.aspect.TrackExecutionTime)")
  public void methodAnnotated() {
  }

  @Around("typeAnnotated() || methodAnnotated()")
  public Object executionTime(ProceedingJoinPoint point) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object object = point.proceed();
    long endTime = System.currentTimeMillis();
    log.info("Class Name: " + point.getSignature().getDeclaringTypeName() + ". Method Name: " + point.getSignature().getName() + ". Time taken for Execution is : " + (endTime - startTime) + "ms");
    return object;
  }

}


