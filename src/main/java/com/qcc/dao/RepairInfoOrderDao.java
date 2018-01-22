package com.qcc.dao;

import com.qcc.domain.RepairInfoOrder;

public interface RepairInfoOrderDao extends BaseRepository<RepairInfoOrder>{
    RepairInfoOrder findRepairInfoOrderByRepairInfoId(Integer repairInfoId);
}
