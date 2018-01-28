package com.qcc.dao;

import com.qcc.domain.HouseLogOrder;
import com.qcc.domain.Tenant;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * 当房东发布租费信息的时候，租客点击确认，生成租费信息订单
 */
public interface HouseLogOrderDao extends BaseRepository<HouseLogOrder>{
    PageImpl<HouseLogOrder> findHouseLogOrderByTenant(Tenant tenant, Pageable pageable);
}
