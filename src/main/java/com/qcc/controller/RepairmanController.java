package com.qcc.controller;

import com.qcc.dao.dto.RepairInfoDto;
import com.qcc.service.RepairInfoService;
import com.qcc.utils.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repairman")
public class RepairmanController {
    @Autowired
    private RepairInfoService repairInfoService;
    public PageVO<List<RepairInfoDto>> findRepairInfos() {
        return null;
    }
}
