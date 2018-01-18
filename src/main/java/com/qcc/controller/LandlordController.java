package com.qcc.controller;

import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.Account;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import com.qcc.service.LandlordService;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/landlord")
public class LandlordController {
    @Autowired
    private LandlordService landlordService;
    @Autowired
    private TenantService tenantService;
    @RequestMapping(value = "/findTenants",method = RequestMethod.GET)
    public ResponseVO<Map<Integer, TenantDto>> findTenants() {
        Account account = CommUtils.getCurrentAccount();
        return tenantService.findTenants(account.getId());
    }
}
