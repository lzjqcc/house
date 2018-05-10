package com.qcc.controller;

import com.qcc.dao.TenantDao;
import com.qcc.dao.dto.HouseLogDto;
import com.qcc.domain.HouseLog;
import com.qcc.domain.Tenant;
import com.qcc.service.HouseLogService;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("houseLog")
public class HouseLogController {
    @Autowired
    private HouseLogService houseLogService;
    @Autowired
    private TenantDao tenantDao;
    @RequestMapping(value = "/publishHouseLog",method = RequestMethod.POST)
    public ResponseVO publishHouseLog(@RequestBody HouseLogDto houseLog) {
        return houseLogService.publishHouseLog(houseLog);
    }
    @RequestMapping(value = "/sureHouseLog", method = RequestMethod.GET)
    public ResponseVO sureHouseLog(@RequestParam Integer houseLogId) {
        Tenant tenant = tenantDao.findTenantByAccount(CommUtils.getCurrentAccount());
        return houseLogService.sureHouseLog(houseLogId, tenant);
    }
    @RequestMapping(value = "findHouselogOrder", method = RequestMethod.GET)
    public PageVO<List<HouseLogDto>> findHouselogOrder(@RequestParam("currentPage")Integer currentPage,
                                                       @RequestParam("size")Integer size) {
        Tenant tenant = tenantDao.findTenantByAccount(CommUtils.getCurrentAccount());
        PageVO<List<HouseLogDto>> listPageVO = new PageVO<>();
        listPageVO.setSize(size);
        listPageVO.setCurrentPage(currentPage);
        return houseLogService.findHouselogOrder(tenant, listPageVO);
    }
    @RequestMapping(value = "findLandPushHouseLog", method = RequestMethod.GET)
    public PageVO<List<HouseLogDto>> findLandPushHouseLog(@RequestParam("currentPage")Integer currentPage,
                                                          @RequestParam("size")Integer size) {
        Tenant tenant = tenantDao.findTenantByAccount(CommUtils.getCurrentAccount());
        PageVO<List<HouseLogDto>> listPageVO = new PageVO<>();
        listPageVO.setSize(size);
        listPageVO.setCurrentPage(currentPage);
        return houseLogService.findLandPushHouseLog(tenant, listPageVO);
    }
}
