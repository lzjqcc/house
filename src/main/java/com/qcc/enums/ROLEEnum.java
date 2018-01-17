package com.qcc.enums;

public  enum ROLEEnum {
    Landlord(1,"landlord","房东"),Tenant(2,"tenant","租客"),Repairman(3,"repairman","维修人员");
    public int code;
    public String description;
    public String value;
    ROLEEnum(int code, String description, String value) {
        this.code = code;
        this.description = description;
        this.value = value;
    }
    }