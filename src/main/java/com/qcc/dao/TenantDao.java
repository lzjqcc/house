package com.qcc.dao;

import com.qcc.domain.House;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TenantDao extends BaseRepository<Tenant> {
    /**
     * 查询房东下的租客
     * @param landlord
     * @param pageable
     * @return
     */
    List<Tenant> findTenantsByLandlord(Landlord landlord, Pageable pageable);

    /**
     * 查询房子下租客
     * @param house
     * @param pageable
     * @return
     */
    List<Tenant> findTenantsByHouse(House house, Pageable pageable);

}
