package com.qcc.config;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.support.json.JSONWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.qcc.domain.Account;
import com.qcc.security.AccountToken;
import com.qcc.security.CustomAuthenticationProcessingFilter;
import com.qcc.security.CustomProvider;
import com.qcc.security.CustomUserDetailsService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.JsonUtils;
import com.qcc.utils.ResponseVO;
import net.minidev.json.JSONUtil;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List list = Lists.newArrayList(new WebExpressionVoter());
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests().mvcMatchers("/account/update").authenticated();
        http.authorizeRequests().mvcMatchers("/tenant/*","/teant/**").access("hasAuthority('tenant')").accessDecisionManager(new AffirmativeBased(list));
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                ResponseVO responseVO = CommUtils.buildReponseVo(false, "没有权限访问", null);
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    response.setContentType("application/json");
                    writer.write(JsonUtils.toJson(responseVO));
                    writer.flush();
                } finally {
                    CommUtils.closeStream(writer);
                }
            }
        });
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                if (authException instanceof InsufficientAuthenticationException) {
                    ResponseVO responseVO = CommUtils.buildReponseVo(false, "匿名用户不能访问", null);
                    PrintWriter writer = null;
                    try {
                        response.setContentType("application/json");
                        writer = response.getWriter();
                        writer.write(JsonUtils.toJson(responseVO));
                        writer.flush();
                    } finally {
                        CommUtils.closeStream(writer);
                    }
                }
            }

        });

    }
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }
    @Bean
    public CustomAuthenticationProcessingFilter authenticationProcessingFilter () throws Exception {
        AuthenticationManager authenticationManager = this.authenticationManager();
        CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter(authenticationManager);
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                PrintWriter writer = null;
                try {


                    if (authentication != null) {
                        ResponseVO<Account> responseVO = new ResponseVO();
                        if (authentication instanceof AccountToken) {
                            AccountToken token = (AccountToken) authentication;
                            responseVO.setResult(token.getAccount());
                            responseVO.setSuccess(true);
                            responseVO.setMessage("登录成功");
                            response.setContentType("application/json");
                            writer = response.getWriter();
                            writer.write(JsonUtils.toJson(responseVO));
                            writer.flush();

                        }
                    }
                } finally {
                    CommUtils.closeStream(writer);
                }
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override

            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                PrintWriter writer = null;
                try {
                    ResponseVO responseVO = new ResponseVO();
                    responseVO.setMessage(exception.getMessage());
                    responseVO.setSuccess(false);
                    response.setContentType("application/json");
                    writer = response.getWriter();
                    writer.write(JsonUtils.toJson(responseVO));
                    writer.flush();
                } finally {
                    CommUtils.closeStream(writer);
                }
            }
        });
        return filter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        CustomProvider provider = new CustomProvider();
        provider.setUserDetailsService(customUserDetailsService());
        auth.authenticationProvider(provider);
    }

}
