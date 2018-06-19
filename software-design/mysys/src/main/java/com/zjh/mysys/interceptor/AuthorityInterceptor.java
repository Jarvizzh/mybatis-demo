package com.zjh.mysys.interceptor;

import com.zjh.mysys.annotation.AuthorityType;
import com.zjh.mysys.entity.Authority;
import com.zjh.mysys.enums.AuthorityNameEnum;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;



public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //Account account = (Account) request.getSession().getAttribute("account");
        List<Authority> authorityList = (List<Authority>) request.getSession().getAttribute("authorityList");
        Integer type = (Integer)request.getSession().getAttribute("type");
        if (type == null) {
            PrintWriter out = response.getWriter();
            out.print("您没有权限执行该操作");
            out.close();
            return false;
        } else if (type == 1){
            return true;
        }
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AuthorityType annotation = method.getAnnotation(AuthorityType.class);

            //当含有注解@AuthorityType时
            if (annotation != null) {
                boolean hasAuthority = false;
                if (authorityList != null) {
                    AuthorityNameEnum[] authorityNameEnums = annotation.name();
                    for (AuthorityNameEnum authorityNameEnum : authorityNameEnums) {
                        switch (authorityNameEnum) {
                            case LOOK:
                                for (Authority authority : authorityList) {
                                    if ("look".equals(authority.getName())) {
                                        hasAuthority = true;
                                        break;
                                    }
                                }
                                break;
                            case EDIT:
                                for (Authority authority : authorityList) {
                                    if ("edit".equals(authority.getName())) {
                                        hasAuthority = true;
                                        break;
                                    }
                                }
                                break;
                        }

                    }
                }
                if (!hasAuthority) {
                    PrintWriter out = response.getWriter();
                    out.print("您没有权限执行该操作");
                    out.close();
                }
                return hasAuthority;
            }

        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }
}
