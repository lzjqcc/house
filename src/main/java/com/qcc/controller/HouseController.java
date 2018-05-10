package com.qcc.controller;

import com.qcc.dao.HouseDao;
import com.qcc.dao.LandlordDao;
import com.qcc.dao.dto.HouseDto;
import com.qcc.dao.dto.HouseLogDto;
import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.Account;
import com.qcc.domain.House;
import com.qcc.domain.HouseLog;
import com.qcc.domain.Landlord;
import com.qcc.service.HouseService;
import com.qcc.service.TenantService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private TenantService tenantService;

    /**
     * 查看房子信息
     * @param houseId
     * @return
     */
    @RequestMapping(value = "/seeHouse")
    public ResponseVO<HouseDto> clickHouse(Integer houseId) {
        return houseService.clickHouse(houseId);
    }
    @RequestMapping(value = "/bastHouse")
    public ResponseVO<List<HouseDto>> findBastHouse() {
        return houseService.findBastHouse();
    }
    /**
     * 根据条件组合来查询房子
     *
     * @param dto
     * @param size
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public PageVO<List<HouseDto>> findHouses(@RequestBody HouseDto dto,
                                             @RequestParam("size") Integer size,
                                             @RequestParam("currentPage") Integer currentPage) {
        PageVO vo = new PageVO();
        vo.setSize(size);
        vo.setCurrentPage(currentPage);
        return houseService.findHouses(dto, vo);
    }

    /**
     * 房东查寻自己的房子
     *
     * @param size
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/findByLanlord", method = RequestMethod.GET)
    public PageVO<List<HouseDto>> findHouseByLandlord(@RequestParam("size") Integer size, @RequestParam("currentPage") Integer currentPage) {
        Account account = CommUtils.getCurrentAccount();
        Landlord landlord = landlordDao.findLandlordByAccount_Id(account.getId());
        PageVO pageVO = new PageVO();
        pageVO.setCurrentPage(currentPage);
        pageVO.setSize(size);
        return houseService.findHouseByLandLoard(landlord, pageVO);
    }

    /**
   * 获取表头条件 ：区域，整租，朝向
     * @return
     */
    @RequestMapping(value = "/tableHead")
    public ResponseVO getTableHead() {
        return houseService.getTableHead();
    }
    /**
     * 查询房租信息
     *
     * @param houseId
     * @param size
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/findHoulogs", method = RequestMethod.GET)
    public PageVO<List<HouseLogDto>> findHouseLogs(@RequestParam("houseId") Integer houseId,
                                                   @RequestParam("size") Integer size,
                                                   @RequestParam("currentPage") Integer currentPage) {
        PageVO pageVO = new PageVO();
        pageVO.setSize(size);
        pageVO.setCurrentPage(currentPage);
        return houseService.findHouseLogs(houseId, pageVO);
    }

    /**
     * 查看house下的租客
     *
     * @return
     */
    @RequestMapping(value = "/findHouseTenants", method = RequestMethod.GET)
    public ResponseVO<Map<Integer, TenantDto>> findHouseTenants(@RequestParam("houseId") Integer houseId) {
        return tenantService.findHouseTenants(houseId);
    }

    /**
     * 发布房子信息
     * @param house
     * @return
     */
    @RequestMapping(value = "/pubilshHouse", method = RequestMethod.POST)
    public ResponseVO publishHouse(@RequestBody House house) {
        return houseService.publishHouse(house, CommUtils.getCurrentAccount());
    }
}
