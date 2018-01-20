package com.qcc.dao.dto;

import com.qcc.domain.Image;
import com.qcc.domain.Landlord;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class HouseDto implements Serializable {
    private static final long serialVersionUID = -545092772283313660L;
    private Integer id;
    private Date updateTime;
    private Date createTime;
    private String description;
    private String address;
    //几室几厅
    private String room;
    // 是否整租
    private Boolean hire;
    // 装修
    private String decoration;
    // 付款方式
    private String payMent;
    // 房屋面积
    private Integer area;
    // 小区周边
    private String surrounding;
    // 配置信息
    private String configInfo;
    // 每个房间面积
    private Integer roomArea;
    // 朝向
    private String direction;
    private String status;
    //特色
    private String characteristic;
    private Set<Image> images;
    private Integer landLordId;
    private String landLordName;
    private String landLordMobile;
    private Boolean landLordGender;
    private String landLordDescribtion;
    private Integer landLordAge;

    public Integer getLandLordId() {
        return landLordId;
    }

    public void setLandLordId(Integer landLordId) {
        this.landLordId = landLordId;
    }

    public String getLandLordName() {
        return landLordName;
    }

    public void setLandLordName(String landLordName) {
        this.landLordName = landLordName;
    }
    public String getLandLordMobile() {

        return landLordMobile;
    }
    public void setLandLordMobile(String landLordMobile) {

        this.landLordMobile = landLordMobile;
    }

    public Boolean getLandLordGender() {
        return landLordGender;
    }

    public void setLandLordGender(Boolean landLordGender) {
        this.landLordGender = landLordGender;
    }

    public String getLandLordDescribtion() {
        return landLordDescribtion;
    }

    public void setLandLordDescribtion(String landLordDescribtion) {
        this.landLordDescribtion = landLordDescribtion;
    }

    public Integer getLandLordAge() {
        return landLordAge;
    }

    public void setLandLordAge(Integer landLordAge) {
        this.landLordAge = landLordAge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Boolean getHire() {
        return hire;
    }

    public void setHire(Boolean hire) {
        this.hire = hire;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getPayMent() {
        return payMent;
    }

    public void setPayMent(String payMent) {
        this.payMent = payMent;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getSurrounding() {
        return surrounding;
    }

    public void setSurrounding(String surrounding) {
        this.surrounding = surrounding;
    }

    public String getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(String configInfo) {
        this.configInfo = configInfo;
    }

    public Integer getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Integer roomArea) {
        this.roomArea = roomArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "HouseDto{" +
                "id=" + id +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", room='" + room + '\'' +
                ", hire=" + hire +
                ", decoration='" + decoration + '\'' +
                ", payMent='" + payMent + '\'' +
                ", area=" + area +
                ", surrounding='" + surrounding + '\'' +
                ", configInfo='" + configInfo + '\'' +
                ", roomArea=" + roomArea +
                ", direction='" + direction + '\'' +
                ", status='" + status + '\'' +
                ", characteristic='" + characteristic + '\'' +
                ", images=" + images +
                ", landLordName='" + landLordName + '\'' +
                ", landLordMobile='" + landLordMobile + '\'' +
                ", landLordGender=" + landLordGender +
                ", landLordDescribtion='" + landLordDescribtion + '\'' +
                ", landLordAge=" + landLordAge +
                '}';
    }
}
