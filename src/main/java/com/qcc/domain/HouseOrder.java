package com.qcc.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * 租房产生的租房订单
 */
@Entity
@Table(name = "tb_house_order")
public class HouseOrder extends BaseEntity  {
    private static final long serialVersionUID = 4825260095110202844L;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;
    @OneToMany(targetEntity = HouseLogOrder.class,fetch = FetchType.LAZY,mappedBy = "houseOrder")
    private Set<HouseLogOrder> houseLogOrders;

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

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Set<HouseLogOrder> getHouseLogOrders() {
        return houseLogOrders;
    }

    public void setHouseLogOrders(Set<HouseLogOrder> houseLogOrders) {
        this.houseLogOrders = houseLogOrders;
    }
}
