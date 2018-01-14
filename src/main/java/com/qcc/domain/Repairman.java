package com.qcc.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// 维修人员
@Entity
@Table(name = "tb_repairman")
public class Repairman extends BaseEntity{
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(targetEntity = RepairInfo.class,mappedBy = "repairman")
    private Set<RepairInfo> repairInfos = new HashSet<RepairInfo>();
    public Account getAccount() {
        return account;
    }

    public Set<RepairInfo> getRepairInfos() {
        return repairInfos;
    }

    public void setRepairInfos(Set<RepairInfo> repairInfos) {
        this.repairInfos = repairInfos;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
