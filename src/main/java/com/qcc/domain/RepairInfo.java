package com.qcc.domain;

import com.qcc.enums.RepairInfoEnum;

import javax.persistence.*;
import java.util.Date;

// 维修信息  房东发布的维修任务，维修人员接受的维修任务
@Entity
@Table(name = "tb_repair_info")
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
    @Enumerated(value = EnumType.ORDINAL)
    private RepairInfoEnum status;
    // 确定维修时间
    private Date repairTime;
    @ManyToOne(targetEntity = House.class,fetch = FetchType.LAZY)
    private House house;

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

    public RepairInfoEnum getStatus() {
        return status;
    }

    public void setStatus(RepairInfoEnum status) {
        this.status = status;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }
}
