package com.qcc.domain;

import com.qcc.enums.ROLE;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Enumerated(value = EnumType.ORDINAL)
    private ROLE role;
    private String mobile;
    private Boolean gender;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;

}
