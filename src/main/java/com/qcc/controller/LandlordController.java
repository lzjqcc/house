package com.qcc.controller;

import com.qcc.dao.dto.RepairInfoDto;
import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.Account;
import com.qcc.service.LandlordService;
import com.qcc.service.RepairInfoService;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/landlord")
public class LandlordController {
    @Autowired
    private LandlordService landlordService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private RepairInfoService repairInfoService;
    /**
     * 显示房东下的租客
     * @param currentPage
     * @param size
     * @return
     */
    @RequestMapping(value = "/findTenants",method = RequestMethod.GET)
    public PageVO<List<TenantDto>> findTenants(@RequestParam("currentPage")Integer currentPage
                                                            , @RequestParam("size") Integer size) {
        Account account = CommUtils.getCurrentAccount();
        PageVO pageVO = new PageVO();
        pageVO.setCurrentPage(currentPage);
        pageVO.setSize(size);
        return tenantService.findLandlordTenants(account.getId() ,pageVO);
    }

    /**
     * 房东查询自己发布的维修任务
     * @param currentPage
     * @param size
     * @return
     */
    @RequestMapping(value = "/findMySelfRepairInfos",method = RequestMethod.GET)
    public PageVO<List<RepairInfoDto>> findMySelfRepairInfo(@RequestParam("currentPage")Integer currentPage,
                                                            @RequestParam("size") Integer size,
                                                            @RequestParam("status") Integer status) {
        PageVO pageVO = new PageVO();
        pageVO.setCurrentPage(currentPage);
        pageVO.setSize(size);
        return repairInfoService.findRepairInfoByLandlord(CommUtils.getCurrentAccount(), pageVO, status);
    }
}
