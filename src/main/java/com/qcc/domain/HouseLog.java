package com.qcc.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

// 租费信息
@Entity
@Table(name = "tb_house_log")
public class HouseLog extends BaseEntity{

    //水费
    private BigDecimal waterExpense;
    //电费
    private BigDecimal electricExpense;
    private BigDecimal price;
}
