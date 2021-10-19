package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import sun.text.normalizer.NormalizerBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
     ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            if(ex instanceof UserException){
                log.info("UserException resolver to 400");
                String accept = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if("application/json".equals(accept)){
                    Map<String,Object> errorResult = new HashMap<>();
                    errorResult.put("ex",ex.getClass());
                    errorResult.put("ex",ex.getMessage());

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    String result = objectMapper.writeValueAsString(errorResult);
                    response.getWriter().write(result);
                    return new ModelAndView();
                }else{
                    //TEXT/HTML
                    return new ModelAndView("error/500");

                }
            }
        }catch (IOException e){
            log.info("resolver ex",e);
        }


        return null;
    }
}