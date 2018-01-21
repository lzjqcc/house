package com.qcc.dao;

import com.qcc.domain.Account;
import com.qcc.domain.House;
import com.qcc.domain.RepairInfo;
import com.qcc.enums.RepairInfoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RepairInfoDao extends BaseRepository<RepairInfo> {
    List<RepairInfo> findRepairInfosByStatusAndHouse(RepairInfoEnum status, House house);

    Page<RepairInfo> findRepairInfosByLandlord_AccountAndStatus(Account account, Integer status, Pageable pageable);
}
