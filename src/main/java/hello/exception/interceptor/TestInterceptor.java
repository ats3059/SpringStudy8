package hello.exception.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exceptionHandler.ErrorResult;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestInterceptor implements HandlerInterceptor {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ErrorResult errorResult = new ErrorResult("400","잘못된 접근입니다.");
        String value = objectMapper.writeValueAsString(errorResult);

        response.getWriter().write(value);



        return false;
    }
}
