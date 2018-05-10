package com.qcc.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RepairInfoDto implements Serializable {
    private static final long serialVersionUID = 920008929796281311L;
    // 确定维修时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date repairTime;
    private Integer repairPrice;
    // 查询条件是否大于 repairPrice
    private Boolean gtPrice;
    private Integer id;
    private String describtion;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String landlordName;
    private Integer landlordId;
    private String houseAddress;
    private String mobile;
    private String repairThing;
    private List<String> imagesURL;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    public static void main(String[] args) {
        RepairInfoDto repairInfoDto = new RepairInfoDto();
        RepairInfoDto dto = new RepairInfoDto();
        System.out.println(repairInfoDto.equals(dto));
        System.out.println(repairInfoDto.hashCode());

    }
}

