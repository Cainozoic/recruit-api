package top.wy.base.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.wy.base.context.WebContext;
import top.wy.base.response.ResResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Component
//@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
//@Order(value = 2)
@Slf4j
public class TokenAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = WebContext.getToken();
        Long currentUserId = WebContext.getCurrentUserId();
        // token不为空,且当前用户ID不为-1  = token有效
        if (token == null || currentUserId == -1) {
            PrintWriter writer = response.getWriter();
            ResResponse resResponse = ResResponse.createFaildResponse("用户授权认证没有通过!客户端请求参数token信息无效");
            String jsonResp = JSON.toJSONString(resResponse);
            writer.print(jsonResp);
            writer.flush();
            writer.close();
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
