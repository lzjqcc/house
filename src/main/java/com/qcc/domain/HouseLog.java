package com.qcc.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

// 租费信息
@Entity
@Table(name = "tb_house_log")
public class HouseLog extends BaseEntity{

    //水费
    private Integer waterExpense;
    //电费
    private Integer electricExpense;
    // 房租费
    private Integer price;
    @ManyToOne(targetEntity = House.class,fetch = FetchType.LAZY)
    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
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
