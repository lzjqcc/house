package com.qcc.domain;

import com.qcc.enums.ROLEEnum;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_account")
public class Account extends BaseEntity{

    @Column(name = "user_name")
    private String userName;

    private String password;
    @Enumerated(value = EnumType.ORDINAL)
    private ROLEEnum role;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLEEnum getRole() {
        return role;
    }

    public void setRole(ROLEEnum role) {
        this.role = role;
    }



}
