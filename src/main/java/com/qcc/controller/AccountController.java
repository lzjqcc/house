package com.qcc.controller;

import com.qcc.dao.dto.AccountDto;
import com.qcc.domain.Account;
import com.qcc.domain.BaseEntity;
import com.qcc.service.AccountService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseVO<? extends BaseEntity> register(@RequestBody Account account, HttpSession session) throws NoSuchFieldException, IllegalAccessException {
        ResponseVO<? extends BaseEntity> responseVO = accountService.register(account);
        if (responseVO.getSuccess()) {
           Field field = ReflectionUtils.findField(responseVO.getResult().getClass(), "account");
           if (field != null) {
               session.setAttribute("account", field.get(responseVO.getResult()));
           }
        }
        return responseVO;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseVO<Account> update(@RequestBody Account accountDto) {
        return accountService.update(accountDto);
    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseVO<Account> get() {
        Account account = CommUtils.getCurrentAccount();
        return CommUtils.buildReponseVo(true, "获取成功", account);
    }
}
