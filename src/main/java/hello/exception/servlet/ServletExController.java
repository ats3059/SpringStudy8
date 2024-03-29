package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletExController {
    //RequestDispatcher 상수로 정의되어 있음
    public static final String ERROR_EXCEPTION_TYPE =
            "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI =
            "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME =
            "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE =
            "javax.servlet.error.status_code";
    public static final String ERROR_EXCEPTION =
            "javax.servlet.error.exception";


    @GetMapping("/error-ex")
    public void errorEx(HttpServletRequest request,HttpServletResponse response) {
        printErrorInfo(request);
        throw new RuntimeException("예외 발생");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.info("errorPage 404");
        printErrorInfo(request);
        response.sendError(404, "404에러");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.info("errorPage 500");
        printErrorInfo(request);
        response.sendError(500, "500에러");
    }

    @GetMapping("/error-400")
    public void error400(HttpServletRequest request,HttpServletResponse response) throws IOException {
        log.info("errorPage 400");
        printErrorInfo(request);
        response.sendError(400, "500에러");
    }

    private void printErrorInfo(HttpServletRequest request) {
        log.info("ERROR_EXCEPTION: ex=", request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE: {}", request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE: {}", request.getAttribute(ERROR_MESSAGE)); // ex의 경우 NestedServletException 스프링이 한번 감싸서 반환
        log.info("ERROR_REQUEST_URI: {}", request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME: {}", request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE: {}", request.getAttribute(ERROR_STATUS_CODE));
        log.info("dispatchType={}", request.getDispatcherType());
    }

}
