package com.theniche.colivin.common.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceIdFilter  extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(TraceIdFilter.class);
    public static final String TRACE_ID = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceId =  UUID.randomUUID().toString();
            MDC.put("traceId", traceId);
            response.setHeader("X-Trace-Id", traceId);

            filterChain.doFilter(request, response);
        }finally {
            MDC.clear();
        }
    }
}
