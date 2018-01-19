package com.qcc.domain;

import com.qcc.enums.ROLEEnum;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_account")
@DynamicUpdate
public class Account extends BaseEntity{

    @Column(name = "user_name")
    private String userName;
    private String password;
    @Enumerated(value = EnumType.ORDINAL)
    private ROLEEnum role;
    private String name;
    private String job;
    private String mobile;
    private Boolean gender;
    private Integer age;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;

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

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
