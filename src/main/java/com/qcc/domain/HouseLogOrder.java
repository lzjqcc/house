package com.qcc.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 每月发布租费信息产生的租费订单
 */
@Entity
@Table(name = "tb_houselog_order")
public class HouseLogOrder extends BaseEntity {
    private static final long serialVersionUID = 7895044566805465718L;
    @ManyToOne
    private Tenant tenant;
    @ManyToOne
    private Landlord landlord;
    //水费
    private Integer waterExpense;
    //电费
    private Integer electricExpense;
    // 房租费
    private Integer price;
    @ManyToOne
    private HouseOrder houseOrder;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
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

    public HouseOrder getHouseOrder() {
        return houseOrder;
    }

    public void setHouseOrder(HouseOrder houseOrder) {
        this.houseOrder = houseOrder;
    }
}
