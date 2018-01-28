package com.qcc.controller;

import com.qcc.annotation.Cache;
import com.qcc.dao.dto.RepairInfoDto;
import com.qcc.dao.dto.RepairInfoOrderDto;
import com.qcc.domain.RepairInfoOrder;
import com.qcc.domain.Repairman;
import com.qcc.service.RepairInfoService;
import com.qcc.service.RepairmanService;
import com.qcc.utils.CacheMap;
import com.qcc.utils.CommUtils;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("repairInfo")
public class RepairInfoController {
    @Autowired
    RepairInfoService repairInfoService;
    @Autowired
    RepairmanService repairmanService;
    @Cache(space = "image")
    CacheMap<List<String>> cacheMap;
    @RequestMapping(value = "/applyRepair",method = RequestMethod.POST)
    public ResponseVO applyRepair(@RequestBody RepairInfoDto dto) {
        List<String> imageURL  = cacheMap.get(CommUtils.getCurrentAccount().getId()+"");
        if (CollectionUtils.isNotEmpty(imageURL)) {
            dto.setImagesURL(imageURL);
        }
        return repairInfoService.pubilshRepairInfo(CommUtils.getCurrentAccount(), dto);
    }
    @RequestMapping(value = "updateRepair", method = RequestMethod.GET)
    public ResponseVO updateRepairInfo(@RequestParam("repairInfoId")Integer repairInfoId,
                                       @RequestParam("status")Integer status) {
        Repairman repairman = repairmanService.findRepairman(CommUtils.getCurrentAccount());
        if (repairman != null) {
          return   repairInfoService.updateRepairInfoStatus(repairInfoId, status, repairman.getId());
        }

        return repairInfoService.updateRepairInfoStatus(repairInfoId, status, null);
    }

    /**
     * 查找任务
     * @param dto
     * @param orderBy
     * @param currentPage
     * @param size
     * @return
     */
    @RequestMapping(value = "findRepairInfos",method = RequestMethod.POST)
    public PageVO findRepairInfo(@RequestBody RepairInfoDto dto, @RequestParam("orderBy")String orderBy,
                                 @RequestParam("currentPage")Integer currentPage,@RequestParam("size")Integer size) {
        PageVO<List<RepairInfoDto>> pageVO = new PageVO<>();
        pageVO.setCurrentPage(currentPage);
        pageVO.setSize(size);
        return repairInfoService.findRepairInfo(dto, pageVO, orderBy);
    }

    /**
     * 维修人员查看订单
     * @param currentPage
     * @param size
     * @return
     */
    @RequestMapping(value = "/findRepairInfoOrder",method = RequestMethod.GET)
    public PageVO findRepairInfoOrder(@RequestParam("currentPage")Integer currentPage, @RequestParam("size")Integer size) {
        Repairman repairman = repairmanService.findRepairman(CommUtils.getCurrentAccount());
        PageVO<List<RepairInfoOrderDto>> pageVO = new PageVO<>();
        pageVO.setCurrentPage(currentPage);
        pageVO.setSize(size);
        if (repairman != null) {
            return repairInfoService.findRepairInfoOrderByRepairman(repairman, pageVO);
        }
        PageVO pageVO1 = new PageVO();
        pageVO1.setEntity("查询失败");
        return pageVO1;
    }
}
