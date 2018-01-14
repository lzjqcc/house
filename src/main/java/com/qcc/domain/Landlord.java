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
    @OneToMany(targetEntity =House.class,mappedBy = "landlord")
    private Set<House> houses = new HashSet<House>();

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

    public Set<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(Set<Tenant> tenants) {
        this.tenants = tenants;
    }
}
