package com.qcc.security;

import com.alibaba.druid.util.StringUtils;
import com.qcc.domain.Account;
import com.qcc.exception.BuisnessException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public CustomAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")) {
            throw new BuisnessException(400, "请求姿势不对");
        }
        Account account = getAccountFromRequest(request);

        AccountToken authRequest = new AccountToken(account.getUserName(), account.getPassword(), null);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 从Json中获取Account对象
     *
     * @param request
     * @return
     * @throws IOException
     */
    private Account getAccountFromRequest(HttpServletRequest request) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            throw new BuisnessException(403, "用户名或密码不能为空");
        }
        Account account = new Account();
        account.setUserName(userName.trim());
        account.setPassword(password.trim());
        return account;
    }


    protected void setDetails(HttpServletRequest request,
                              AccountToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
