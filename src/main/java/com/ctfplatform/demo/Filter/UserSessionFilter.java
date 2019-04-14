package com.ctfplatform.demo.Filter;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserSessionFilter",urlPatterns = {"/team/queryteambyname","/team/insertTeam","/user/updateuser","/CompetitionUser/addCompetitionUser","/CompetitionUser/addCompetitionUser"})  //查找队伍、创建队伍、更新自身
@Order(value = 2)
public class UserSessionFilter implements Filter {
    //表示当前用户未登录
    String NO_LOGIN = "您还未登录";
    //不需要登录就可以访问的路径 地址
    String[] includeUrls = new String[]{"/user/login","user/adduser","/competition/listcompetition"};   //登录注册查看比赛


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);
        if(!needFilter){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            if(session!=null&&session.getAttribute("user")!=null){
                filterChain.doFilter(request,response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);
                }else{
                    response.sendRedirect(request.getContextPath()+"/user/login");   //重定向地址
                }
                return;
            }
        }
    }
    //是否需要过滤
    public boolean isNeedFilter(String uri){
        for(String includeUrl : includeUrls){
            if(includeUrl.equals(uri)){
                return false;
            }
        }
        return true;
    }
    @Override
    public void destroy() {

    }
}
