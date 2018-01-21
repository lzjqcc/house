package com.qcc.domain;

import com.qcc.enums.RepairInfoEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

// 维修信息  房东发布的维修任务，维修人员接受的维修任务
@Entity
@Table(name = "tb_repairinfo")
public class RepairInfo extends BaseEntity{
    //租客
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Tenant.class)
    private Tenant tenant;
    //房东
    @ManyToOne(targetEntity = Landlord.class,fetch = FetchType.LAZY)
    private Landlord landlord;
    // 维修人员
    @ManyToOne(targetEntity = Repairman.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "repairman_id",referencedColumnName = "id")
    private Repairman repairman;
    // @link RepairInfoEnum
    private Integer status;
    // 确定维修时间
    private Date repairTime;
    @ManyToOne(targetEntity = House.class,fetch = FetchType.LAZY)
    private House house;
    private Integer repairPrice;
    private String describtion;
    private String repairThing;
    @OneToMany
    private Set<Image> images;

    public String getRepairThing() {
        return repairThing;
    }

    public void setRepairThing(String repairThing) {
        this.repairThing = repairThing;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Integer getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Integer repairPrice) {
        this.repairPrice = repairPrice;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

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

    public Repairman getRepairman() {
        return repairman;
    }

    public void setRepairman(Repairman repairman) {
        this.repairman = repairman;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }
}
