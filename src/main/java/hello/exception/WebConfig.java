package hello.exception;

import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","*.ico","/error","/error-page/**"); // 오류 페이지 경로를 넣어버려서 인터셉터가 동작 안하도록 한다

    }




//    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new LogFilter());
        filterRegistration.setOrder(1);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ERROR);


        return filterRegistration;
    }


}
