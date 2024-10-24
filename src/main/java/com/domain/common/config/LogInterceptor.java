package com.domain.common.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * HTTP 거래에 대한 로그 인터셉터이다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 거래 시작 핸들러
     *
     * @param request  요청데이터
     * @param response 응답 데이터
     * @param handler  핸들러
     * @return 항상 참
     * @throws Exception 예외
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        log.info("==================          START         ==================");
        log.info("{} {}", request.getMethod(), request.getServletPath());

        return true;
    }

    /**
     * 거래 종료 핸들러
     *
     * @param request   요청데이터
     * @param response  응답 데이터
     * @param handler   핸들러
     * @param exception 예외
     * @throws Exception 예외
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception exception) throws Exception {

        log.info("{} {} ", request.getMethod(), request.getServletPath());
        log.info("==================           END          ==================");
    }
}
