package com.qcc.dao;

import com.qcc.domain.HouseOrder;
import com.qcc.domain.Tenant;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


/**
 * 租客点击租房的时候，生成租房订单
 */
public interface HouseOrderDao extends BaseRepository<HouseOrder> {
    PageImpl<HouseOrder> findHouseOrderByTenant(Tenant tenant, Pageable pageable);
}
