package com.qcc.dao;

import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordDao extends BaseRepository<Landlord>{
    Landlord findLandlordByAccount_Id(Integer accountId);

    Landlord findLandlordByTenants(Tenant tenant);
}
