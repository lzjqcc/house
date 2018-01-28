package com.qcc.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RepairInfoOrderDto {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer repairPrice;
    private String houseName;
    private String landlordName;
    private String landLordMobile;
    private String houseAddress;
    // 维修的东西
    private String repairThing;
    // 维修时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date repaireTime;
    private Integer repairInfoId;
    private Integer status;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getRepairInfoId() {
        return repairInfoId;
    }

    public void setRepairInfoId(Integer repairInfoId) {
        this.repairInfoId = repairInfoId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
