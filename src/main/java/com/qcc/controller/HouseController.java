package com.qcc.controller;

import com.qcc.dao.LandlordDao;
import com.qcc.dao.dto.HouseDto;
import com.qcc.dao.dto.HouseLogDto;
import com.qcc.domain.Account;
import com.qcc.domain.HouseLog;
import com.qcc.domain.Landlord;
import com.qcc.service.HouseService;
import com.qcc.utils.CommUtils;
import com.qcc.utils.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired
    private LandlordDao landlordDao;

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
     * 查询房租信息
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
}
