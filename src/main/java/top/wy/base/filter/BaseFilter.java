package top.wy.base.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.wy.base.context.WebContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "BaseFilter",urlPatterns = "/*")
@Slf4j
public class BaseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        WebContext.setRequestAndResponse(request,response);
        String token = request.getHeader("token");
        WebContext.setToken(token);
        chain.doFilter(request,response);
    }
}
