package com.qcc.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_house")
public class House extends BaseEntity{
    private Integer price;
    //房屋描述
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
    @Lob
    @Column(columnDefinition="TEXT")
    private String address;
    //几室几厅
    private String room;
    // 是否整租
    private Boolean hire;
    // 装修
    @Lob
    @Column(columnDefinition="TEXT")
    private String decoration;
    // 付款方式
    private String payMent;
    // 房屋面积
    private Integer area;
    @ManyToOne(targetEntity = Landlord.class)
    private Landlord landlord;
    // 小区周边
    @Lob
    @Column(columnDefinition="TEXT")
    private String surrounding;
    // 配置信息
    @Lob
    @Column(columnDefinition="TEXT")
    private String configInfo;
    // 每个房间面积
    private Integer roomArea;
    // 朝向
    private String direction;
    private String status;
    //特色
    @Lob
    @Column(columnDefinition="TEXT")
    private String characteristic;
    // 租客信息
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY, targetEntity = Tenant.class)
    private Set<Tenant> tenants = new HashSet<Tenant>();
    @OneToMany(targetEntity = Image.class,mappedBy = "house",fetch = FetchType.LAZY)
    private Set<Image> images = new HashSet<Image>();
    // 因为每个月都会产生租费信息 所以这里时 N
    @OneToMany(fetch = FetchType.LAZY,targetEntity = HouseLog.class,mappedBy = "house")
    private Set<HouseLog> houseLogs = new HashSet<HouseLog>();
    // 因为这个房子可能会维修多次
    @OneToMany(targetEntity = RepairInfo.class, mappedBy = "house")
    private Set<RepairInfo> repairInfos = new HashSet<RepairInfo>();

    public Set<RepairInfo> getRepairInfos() {
        return repairInfos;
    }

    public void setRepairInfos(Set<RepairInfo> repairInfos) {
        this.repairInfos = repairInfos;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public Set<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(Set<Tenant> tenants) {
        this.tenants = tenants;
    }

    public Set<HouseLog> getHouseLogs() {
        return houseLogs;
    }

    public void setHouseLogs(Set<HouseLog> houseLogs) {
        this.houseLogs = houseLogs;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
