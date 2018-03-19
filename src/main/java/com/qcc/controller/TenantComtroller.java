package com.qcc.controller;

import com.qcc.dao.TenantDao;
import com.qcc.domain.Tenant;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tenant")
public class TenantComtroller {
    @Autowired
    private TenantService tenantService;
    @Autowired
    TenantDao tenantDao;

    /**
     * 租客租房
     * @param houseId
     * @return
     */
    @RequestMapping(value = "/tenantHouse", method = RequestMethod.GET)
    public ResponseVO tenantHouse(Integer houseId) {
        Tenant tenant =tenantDao.findTenantByAccount(CommUtils.getCurrentAccount());
        if (tenant != null) {
            return tenantService.tenantHouse(houseId, tenant);
        }
        return CommUtils.buildReponseVo(false, "操作失败", null);

    }

    /**
     * 租客退租
     * @return
     */
    @RequestMapping(value = "/removeHouseRelation", method = RequestMethod.GET)
    public ResponseVO removeHouse() {
        Tenant tenant = tenantDao.findTenantByAccount(CommUtils.getCurrentAccount());
        return tenantService.deleteHouseTenantRelation(tenant.getId());
    }
}
