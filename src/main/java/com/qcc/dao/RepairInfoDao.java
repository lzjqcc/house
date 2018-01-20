package com.qcc.dao;

import com.qcc.domain.House;
import com.qcc.domain.RepairInfo;
import com.qcc.enums.RepairInfoEnum;

import java.util.List;

public interface RepairInfoDao extends BaseRepository<RepairInfo> {
    List<RepairInfo> findRepairInfosByStatusAndHouse(RepairInfoEnum status, House house);
}
