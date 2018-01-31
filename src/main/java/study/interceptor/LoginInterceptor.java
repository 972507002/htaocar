package study.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LoginInterceptor implements HandlerInterceptor{
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取session里的登录状态值
        String requestUrl=request.getRequestURI();
        String method = request.getMethod();
        String params = getParams(request);

        logger.info("请求路径requestUrl:{}, -提交类型 method:{}"+",-请求参数 paramList :{" +params+ "}",requestUrl,method);
        return true;
    }

    //获取请求参数
    private String getParams(HttpServletRequest request){

        StringBuilder sb = new StringBuilder();
        Enumeration<String> enu = request.getParameterNames();// 类型的枚举
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            sb.append(name + "=" + request.getParameter(name) );
        }
        return sb.toString();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
