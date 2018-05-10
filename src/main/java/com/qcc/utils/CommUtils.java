package com.qcc.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.qcc.domain.Account;
import com.qcc.domain.BaseEntity;
import com.qcc.security.AccountToken;
import org.assertj.core.util.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommUtils {
    public static final String IMAGE_DIR = "file:D:\\qccImage";
    public static final String HOST = "http://192.168.1.125:8080/";
    public static <T> ResponseVO<T> buildReponseVo(Boolean success, String message , T t) {
        ResponseVO<T> responseVO = new ResponseVO();
        responseVO.setSuccess(success);
        responseVO.setMessage(message);
        if (t != null) {
               responseVO.setResult(t);
        }
        return responseVO;
    }

    public static String getImageURL(String path) {
        if (StringUtils.isEmpty(path)) {
            return "";
        }
        String [] splits = path.split("\\\\");
        return HOST +"image/"+splits[splits.length-2]+"/"+splits[splits.length - 1];
    }
    public static void updateCurrentAccount(Account account) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AccountToken) {
            AccountToken accountToken = (AccountToken) authentication;
            accountToken.setAccount(account);
            SecurityContextHolder.getContext().setAuthentication(accountToken);
        }
    }
    public static String[] findNullFieldName(Object entity) {
        List<Field> allFileds = getAllFields(entity.getClass(), new ArrayList<>());
        List<String> nullFiledNames = new ArrayList<>();
        try {
            for (Field field : allFileds) {
                field.setAccessible(true);
                Object object = field.get(entity);
                if (object == null) {
                    nullFiledNames.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            return null;
        }
        String[] array = new String[nullFiledNames.size()];
        return nullFiledNames.toArray(array);
    }
    private static List<Field> getAllFields(Class entityClass, List<Field> list) {
        Field[] allFields = entityClass.getDeclaredFields();
        list.addAll(Lists.newArrayList(allFields));
        if (!entityClass.getSuperclass().equals(Object.class)) {
            getAllFields(entityClass.getSuperclass(), list);
        }
        return list;
    }
    public static Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AccountToken) {
            Account account = ((AccountToken) authentication).getAccount();
            return account;
        }
        return new Account();
    }
    /*public static Account setCurrentAccount(Account account) {
        SecurityContextHolder.getContext().setAuthentication(account);
    }*/
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

    }
}
