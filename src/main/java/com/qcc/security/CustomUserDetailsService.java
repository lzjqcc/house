package com.qcc.security;

import com.qcc.dao.AccountDao;
import com.qcc.dao.dto.AccountDto;
import com.qcc.domain.Account;
import com.qcc.service.AccountService;
import javafx.beans.property.SimpleListProperty;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private AccountDao accountDao;
    public Authentication loadAccount(String userName, String password) {
        Account account = accountDao.findAccountByUserNameAndPassword(userName, password);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        AccountToken token = new AccountToken(account.getUserName(), account.getPassword(), Lists.newArrayList(new SimpleGrantedAuthority(account.getRole().description)));
        token.setAccount(account);
       return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
