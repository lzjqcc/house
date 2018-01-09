package com.qcc.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal price;
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
}
