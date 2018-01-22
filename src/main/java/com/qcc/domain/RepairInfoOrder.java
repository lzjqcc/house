package com.qcc.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 维修费用产生的维修费用订单  维修人员点击维修完成会产生一个维修订单
 */
@Entity
@Table(name = "tb_repairinfo_order")
public class RepairInfoOrder extends BaseEntity {
    private static final long serialVersionUID = 7487432311677956253L;
    @ManyToOne
    private Repairman repairman;
    private Integer repairPrice;
    private String houseName;
    private String landlordName;
    private String landLordMobile;
    private String houseAddress;
    // 维修的东西
    private String repairThing;
    // 维修时间
    private Date repaireTime;
    private Integer repairInfoId;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRepairInfoId() {
        return repairInfoId;
    }

    public void setRepairInfoId(Integer repairInfoId) {
        this.repairInfoId = repairInfoId;
    }

    public String getRepairThing() {
        return repairThing;
    }

    public void setRepairThing(String repairThing) {
        this.repairThing = repairThing;
    }

    public Date getRepaireTime() {
        return repaireTime;
    }

    public void setRepaireTime(Date repaireTime) {
        this.repaireTime = repaireTime;
    }

    public Repairman getRepairman() {
        return repairman;
    }

    public void setRepairman(Repairman repairman) {
        this.repairman = repairman;
    }

    public Integer getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Integer repairPrice) {
        this.repairPrice = repairPrice;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public String getLandLordMobile() {
        return landLordMobile;
    }

    public void setLandLordMobile(String landLordMobile) {
        this.landLordMobile = landLordMobile;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
