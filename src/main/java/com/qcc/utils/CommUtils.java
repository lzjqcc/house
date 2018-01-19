package com.qcc.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.qcc.domain.Account;
import com.qcc.domain.BaseEntity;
import com.qcc.security.AccountToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommUtils {

    public static <T> ResponseVO<T> buildReponseVo(Boolean success, String message , T t) {
        ResponseVO<T> responseVO = new ResponseVO();
        responseVO.setSuccess(success);
        responseVO.setMessage(message);
        if (t != null) {
               responseVO.setResult(t);
        }
        return responseVO;
    }
    public static Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AccountToken) {
            Account account = ((AccountToken) authentication).getAccount();
            return account;
        }
        return new Account();
    }
    public static void closeStream(Closeable stream){
        if (stream!=null){
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(JSONUtils.toJSONString(CommUtils.buildReponseVo(false, "skldf", null)));
    }
}
