package com.qcc.dao.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RepairInfoDto implements Serializable {
    private static final long serialVersionUID = 920008929796281311L;
    // 确定维修时间
    private Date repairTime;
    private Integer repairPrice;
    // 查询条件是否大于 repairPrice
    private Boolean gtPrice;
    private Integer id;
    private String describtion;
    private Date createTime;
    private String landlordName;
    private Integer landlordId;
    private String houseAddress;
    private String landlordMobile;
    private String repairThing;
    private List<String> imagesURL;
    private Integer houseId;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public Integer getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Integer repairPrice) {
        this.repairPrice = repairPrice;
    }

    public Boolean getGtPrice() {
        return gtPrice;
    }

    public void setGtPrice(Boolean gtPrice) {
        this.gtPrice = gtPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getLandlordMobile() {
        return landlordMobile;
    }

    public void setLandlordMobile(String landlordMobile) {
        this.landlordMobile = landlordMobile;
    }

    public String getRepairThing() {
        return repairThing;
    }

    public void setRepairThing(String repairThing) {
        this.repairThing = repairThing;
    }

    public List<String> getImagesURL() {
        return imagesURL;
    }

    public void setImagesURL(List<String> imagesURL) {
        this.imagesURL = imagesURL;
    }
}

