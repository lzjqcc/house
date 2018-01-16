package com.qcc.dao.dto;

import com.qcc.domain.Account;
import com.qcc.domain.House;

import java.io.Serializable;

public class TenantDto implements Serializable {
    private static final long serialVersionUID = 3291110257743455020L;
    private Account account;
    private HouseDto house;
}
