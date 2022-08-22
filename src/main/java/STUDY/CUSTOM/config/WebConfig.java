package STUDY.CUSTOM.config;

import STUDY.CUSTOM.filter.AuthFilter;
import STUDY.CUSTOM.token.TokenExtractor;
import STUDY.CUSTOM.token.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
//@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

//    AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean tokenFilter(TokenExtractor tokenExtractor, TokenValidator tokenValidator) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthFilter(tokenExtractor, tokenValidator));
//        filterRegistrationBean.setOrder(1);
        //필터를 적용할 URL 패턴을 지정하며, 하나 이상의 패턴을 지정 할 수도 있다.
//        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
