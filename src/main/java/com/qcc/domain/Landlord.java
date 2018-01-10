package com.qcc.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
// 房东
@Entity
@Table(name = "tb_landlord")
public class Landlord extends BaseEntity{

    // 登陆信息
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Account account;
    @OneToMany
    private Set<House> houses = new HashSet<House>();
    private String name;
    private String job;
    private String mobile;
    private Boolean gender;
    private Integer age;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
    // 租客
    @ManyToMany
    private Set<Tenant> tenants = new HashSet<Tenant>();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(Set<Tenant> tenants) {
        this.tenants = tenants;
    }
}
