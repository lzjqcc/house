package com.qcc.controller;

import com.qcc.dao.TenantDao;
import com.qcc.domain.Tenant;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tenant")
public class TenantComtroller {
    @Autowired
    private TenantService tenantService;
    @Autowired
    TenantDao tenantDao;

    /**
     * 添加租客
     * @param houseId
     * @return
     */
    @RequestMapping(value = "/addTenant", method = RequestMethod.GET)
    public ResponseVO tenantHouse(@RequestParam("userName") String userName,
                                  @RequestParam("houseId") Integer houseId) {
        Tenant tenant =tenantDao.findTenantByAccount_UserName(userName);
        if (tenant != null) {
            return tenantService.tenantHouse(houseId, tenant);
        }
        return CommUtils.buildReponseVo(false, "操作失败", null);

    }
    @RequestMapping(value = "/myLandlord", method = RequestMethod.GET)
    public ResponseVO myLandlord() {
        return tenantService.findMyLandlord();
    }
    /**
     * 租客退租
     * @return
     */
    @RequestMapping(value = "/removeHouseRelation", method = RequestMethod.GET)
    public ResponseVO removeHouse(@RequestParam("accountId")Integer accountId,@RequestParam("houseId")Integer houseId) {
        Tenant tenant = tenantDao.findTenantByAccount_Id(accountId);
        if (tenant == null) {
            return CommUtils.buildReponseVo(false, "操作失败", null);
        }
        return tenantService.deleteHouseTenantRelation(tenant.getId(), houseId);
    }
}
