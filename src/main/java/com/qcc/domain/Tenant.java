package com.qcc.domain;

import javax.persistence.*;
import java.util.Set;

// 租客
@Entity
@Table(name = "tb_tenant")
public class Tenant extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private String name;
    private String job;
    private String mobile;
    private Boolean gender;
    private Integer age;
    @ManyToOne(targetEntity = House.class,fetch = FetchType.LAZY)
    @JoinTable(name = "tb_tenant_house",joinColumns = @JoinColumn(name = "tenant_id",unique = false),inverseJoinColumns = @JoinColumn(name = "house_id",unique = false))
    private House house;
    @ManyToOne(targetEntity = Landlord.class,fetch = FetchType.LAZY)
    @JoinTable(name = "tb_tenant_landlord",joinColumns = @JoinColumn(name = "tenant_id"),inverseJoinColumns = @JoinColumn(name = "landlord_id"))
    private Landlord landlord;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
