package com.qcc.controller;

import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.Account;
import com.qcc.service.LandlordService;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/landlord")
public class LandlordController {
    @Autowired
    private LandlordService landlordService;
    @Autowired
    private TenantService tenantService;
    @RequestMapping(value = "/findTenants",method = RequestMethod.GET)
    public ResponseVO<Map<Integer, TenantDto>> findTenants(@RequestParam("currentPage")Integer currentPage,@RequestParam("size") Integer size) {
        Account account = CommUtils.getCurrentAccount();
        PageRequest request = new PageRequest(currentPage - 1, size);
        return tenantService.findLandlordTenants(account.getId() ,request);
    }
}
