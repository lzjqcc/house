package com.qcc.enums;

public  enum ROLEEnum {
    Landlord(1,"房东"),Tenant(2,"租客"),Repairman(3,"维修人员");
    public int code;
    public String description;
    ROLEEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
    }