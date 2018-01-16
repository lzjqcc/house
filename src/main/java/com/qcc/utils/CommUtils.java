package com.qcc.utils;

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
}
