package com.theniche.colivin.common.aop;

import com.theniche.colivin.common.aop.annotation.Audit;
import com.theniche.colivin.common.logging.TraceIdFilter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {
    private static final Logger log = LoggerFactory.getLogger(AuditAspect.class);
    String tracId = MDC.get(TraceIdFilter.TRACE_ID);

    @Around("@annotation(com.theniche.colivin.common.aop.annotation.Audit)")
    public Object audit(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Audit audit = method.getAnnotation(Audit.class);

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();

        long startTime = System.currentTimeMillis();

        if (audit.logArgs()) {
            log.info(
                    "🔍 AUDIT START | traceId={} | action={} | {}.{} | args={}",
                    tracId,
                    audit.action(),
                    className,
                    methodName,
                    Arrays.toString(joinPoint.getArgs())
            );
        } else {
            log.info(
                    "🔍 AUDIT START | traceId={} |action={} | {}.{}",
                    tracId,
                    audit.action(),
                    className,
                    methodName
            );
        }

        try {
            Object result = joinPoint.proceed();

            long duration = System.currentTimeMillis() - startTime;

            if (audit.logResult()) {
                log.info(
                        "✅ AUDIT SUCCESS | traceId={} |action={} | {}.{} | duration={}ms | result={}",
                        tracId,
                        audit.action(),
                        className,
                        methodName,
                        duration,
                        result
                );
            } else {
                log.info(
                        "✅ AUDIT SUCCESS | traceId={} |action={} | {}.{} | duration={}ms",
                        tracId,
                        audit.action(),
                        className,
                        methodName,
                        duration
                );
            }

            return result;

        } catch (Exception ex) {
            long duration = System.currentTimeMillis() - startTime;

            log.error(
                    "❌ AUDIT FAILURE | traceId={} action={} | {}.{} | duration={}ms | error={}",
                    tracId,
                    audit.action(),
                    className,
                    methodName,
                    duration,
                    ex.getMessage(),
                    ex
            );

            throw ex;
        }
    }
}
