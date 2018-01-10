package com.qcc.domain;

import javax.persistence.*;
import java.util.Date;
// 维修人员
@Entity
@Table(name = "tb_repairman")
public class Repairman extends BaseEntity{
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name = "account_id")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
