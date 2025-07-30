package com.aston.frontendpracticeservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(* com.aston.frontendpracticeservice.controller..*.*(..))")
    public void  anyControllerMethod() {}

    @Pointcut("execution(* com.aston.frontendpracticeservice.service..*.*(..))")
    public void anyServiceMethod() {}

    @Around("anyControllerMethod() || anyServiceMethod()")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        var className = joinPoint.getTarget().getClass().getName();
        var methodName = joinPoint.getSignature().getName();
        var args = returnArgs(joinPoint);

        log.debug("Invoked {} method in class {}, with args: {}", methodName, className, args);
        try {
            var result = joinPoint.proceed();
            log.debug("Invoked {} method in class {} return {}", methodName, className, result);
            return result;
        } catch (Throwable ex) {
            log.error("Invoked {} method in class {} throw {} class with message: {}",
                    methodName, className, ex.getClass(), ex.getMessage());
            throw ex;
        }
    }

    private String returnArgs(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] params = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        return IntStream.range(0, args.length)
                .mapToObj( i -> String.format("%s = %s", params[i], args[i]))
                .collect(Collectors.joining(", "));

    }
}
