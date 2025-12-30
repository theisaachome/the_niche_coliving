package com.theniche.colivin.common.aop.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Audit {
    /**
     * Business action name (e.g. tenant.search, tenant.create)
     */
    String action();

    /**
     * Whether to log method arguments
     */
    boolean logArgs() default true;

    /**
     * Whether to log return value
     */
    boolean logResult() default false;
}
