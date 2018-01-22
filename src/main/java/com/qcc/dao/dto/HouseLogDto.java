package com.qcc.dao.dto;

import java.io.Serializable;
import java.util.Date;

public class HouseLogDto implements Serializable{
    private static final long serialVersionUID = 7214966397951822701L;
    private Integer id;
    private Date createTime;
    //水费
    private Integer waterExpense;
    //电费
    private Integer electricExpense;
    // 房租费
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getWaterExpense() {
        return waterExpense;
    }

    public void setWaterExpense(Integer waterExpense) {
        this.waterExpense = waterExpense;
    }

    public Integer getElectricExpense() {
        return electricExpense;
    }

    public void setElectricExpense(Integer electricExpense) {
        this.electricExpense = electricExpense;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
