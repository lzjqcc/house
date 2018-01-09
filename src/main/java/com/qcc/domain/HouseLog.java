package com.qcc.domain;

import java.math.BigDecimal;
import java.util.Date;

// 租费信息
public class HouseLog {
    private Integer id;
    //水费
    private BigDecimal waterExpense;
    //电费
    private BigDecimal electricExpense;
    private BigDecimal price;
    private Date updateTime;
    private Date createTime;
}
