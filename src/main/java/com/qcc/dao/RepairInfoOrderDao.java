package com.qcc.dao;

import com.qcc.domain.RepairInfoOrder;
import com.qcc.domain.Repairman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 维修人员接受维修任务的时候，生成维修订单
 */
public interface RepairInfoOrderDao extends BaseRepository<RepairInfoOrder>{
    RepairInfoOrder findRepairInfoOrderByRepairInfoId(Integer repairInfoId);

    PageImpl<RepairInfoOrder> findRepairInfoOrderByRepairman(Repairman repairman, Pageable pageable);

}
