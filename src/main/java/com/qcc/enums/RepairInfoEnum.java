package com.qcc.enums;

public enum  RepairInfoEnum {
    TENANT_APPLY(1,"租客申请"),LANDLORD_AGREE(2,"房东同意"),LANDLORD_REFUNSE(3,"房东拒绝"),LANDLORD_PUB(4,"房东发布"),LANDLORD_REVOKE(5,"房东撤销"),
    REPAIRMAN_RECEIVE(6,"维修人员接受"),REPAIRMAN_COMPLETE(7,"维修完成");
    public int code;
    public String value;
    RepairInfoEnum(int code , String value) {
        this.code = code;
        this.value = value;
    }
}
