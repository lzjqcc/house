package com.qcc.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
// 房东
@Entity
@Table(name = "tb_")
public class Landlord {
    private Integer id;
    // 登陆信息
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
    // 租客的评价
    private Set<Comment> comments = new HashSet<Comment>();
    // 租客
    private Set<Tenant> tenants = new HashSet<Tenant>();
}
