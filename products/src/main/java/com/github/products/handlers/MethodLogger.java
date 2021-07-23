package com.github.products.handlers;

import com.github.products.utils.Logging;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class MethodLogger {

    @Before(value = "@annotation(com.github.products.utils.Logging)")
    public void logBefore(JoinPoint joinPoint) {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}.",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(
            pointcut = "@annotation(com.github.products.utils.Logging)",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Logging.class) && isDebugEnableAndIsReturnTrue(method)) {
            log.debug("Enter: {}.{}() with argument[s] {} returned result {}.",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()),
                    result);
        }
    }

    private static boolean isDebugEnableAndIsReturnTrue(Method method) {
        return log.isDebugEnabled() && method.getAnnotation(Logging.class).isReturn();
    }

    @Around("@annotation(com.github.products.utils.Logging)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(Logging.class) && isDebugEnableAndIsTimeTrue(method)) {
            long start = System.currentTimeMillis();
            try {
                Object result = joinPoint.proceed();
                long elapsedTime = System.currentTimeMillis() - start;
                log.debug("Enter: {}.{}() with argument[s] {} returned result {}, time {}.",
                        signature.getDeclaringTypeName(),
                        signature.getName(),
                        Arrays.toString(joinPoint.getArgs()),
                        result, elapsedTime);
                return result;
            } catch (IllegalArgumentException e) {
                log.error("Enter: {}.{}() with argument[s] {} throw exception {}",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(),
                        Arrays.toString(joinPoint.getArgs()),
                        e);
                throw e;
            }
        }
        return joinPoint.proceed();
    }

    private static boolean isDebugEnableAndIsTimeTrue(Method method) {
        return log.isDebugEnabled() && method.getAnnotation(Logging.class).isTime();
    }

    @AfterThrowing(
            pointcut = "@annotation(com.github.products.utils.Logging)",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] {}.",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
        if (log.isErrorEnabled()) {
            log.error("Enter: {}.{}() with argument[s] {} throw exception {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()),
                    error.getMessage());
        }
    }

}
