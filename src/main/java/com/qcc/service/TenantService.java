package com.qcc.service;

import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.dao.TenantDao;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class TenantService {
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private AccountDao accountDao;
    /*@PostConstruct
    public void inject() {
        Tenant tenant = new Tenant();
        tenant.setAccount(accountDao.findOne(3));
        tenantDao.save(tenant);
        Tenant tenant1 = new Tenant();
        tenant1.setAccount(accountDao.findOne(4));
        tenantDao.save(tenant1);
    }*/
    public ResponseVO<Set<Tenant>> findTenants(Integer id) {
        Landlord landlord = landlordDao.findOne(id);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, landlord.getTenants());
    }
}
