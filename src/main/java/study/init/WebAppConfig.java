package study.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import study.interceptor.LoginInterceptor;


/**
 * Created by Administrator on 2017/9/30.
 */
//@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 注解使用拦截器
     * addPathPatterns 用于添加拦截规则
     * 多次添加多个拦截器组成一个拦截器链
     * @param registry
     */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
//            /**拦截除了toLogin 和 login 之外的所有请求*/
//            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin","/login");
            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
            super.addInterceptors(registry);
        }
    }
