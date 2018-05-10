package com.qcc.service;

import com.qcc.dao.*;
import com.qcc.dao.dto.HouseLogDto;
import com.qcc.domain.*;
import com.qcc.utils.CommUtils;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import javassist.runtime.Desc;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseLogService {
    @Autowired
    private HouseLogDao houseLogDao;
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private HouseOrderDao houseOrderDao;
    @Autowired
    private HouseLogOrderDao houseLogOrderDao;
    @Autowired
    private TenantDao tenantDao;
    /**
     * 房东发布租费信息
     * houseLog中house --》houseId不能为null
     *           tennet--->tenantId 不能为null
     * @param houseLog
     * @return
     */
    public ResponseVO<HouseLogDto> publishHouseLog(HouseLogDto houseLog) {
        if (houseLog == null || houseLog.getHouseId() == null || houseLog.getTenantId() ==null  ) {
            return CommUtils.buildReponseVo(false, "houseId || tenantId == null", null);
        }
        HouseLog houseLog1 = new HouseLog();
        BeanUtils.copyProperties(houseLog, houseLog1);
        House house = houseDao.findOne(houseLog.getHouseId());
        houseLog1.setSure(false);
        houseLog1.setTenant(tenantDao.findOne(houseLog.getTenantId()));
        houseLog1.setHouse(house);
        houseLogDao.save(houseLog1);
        return CommUtils.buildReponseVo(true,"操作成功", null);
    }

    /**
     * 租客确认租费信息
     *
     * @return
     */
    public ResponseVO sureHouseLog(Integer houseLogId,Tenant tenant) {
        HouseLog houseLog = houseLogDao.findOne(houseLogId);
        houseLog.setSure(true);

        HouseLogOrder order = new HouseLogOrder();
        PageImpl<HouseOrder> page = houseOrderDao.findHouseOrderByTenant(tenant, new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "createTime")));
        if (page.getContent().size() == 1) {
            houseLogDao.save(houseLog);
            order.setHouseOrder(page.getContent().get(0));
            BeanUtils.copyProperties(houseLog,order);
            houseLogOrderDao.save(order);
            return CommUtils.buildReponseVo(true, "操作成功", null);
        }
        return CommUtils.buildReponseVo(false, "操作失败", null);
    }

    /**
     * 租客查看已确认的租费信息
     * @param tenant
     * @param pageVO
     * @return
     */
    public PageVO<List<HouseLogDto>> findHouselogOrder(Tenant tenant, PageVO<List<HouseLogDto>> pageVO) {
        PageImpl<HouseLogOrder> page = houseLogOrderDao.findHouseLogOrderByTenant(tenant, getPageRequest(pageVO));
        buildPageVo(pageVO, page);
        return pageVO;
    }
    /**
     * 租客查看房东发布的租费信息
     * @param tenant
     * @return
     */
    public PageVO<List<HouseLogDto>> findLandPushHouseLog(Tenant tenant, PageVO<List<HouseLogDto>> pageVO) {
        PageImpl<HouseLog> page = houseLogDao.findHouseLogByTenantAndSure(tenant, false,  getPageRequest(pageVO));
        buildPageVo(pageVO, page);
        return pageVO;
    }
    private PageRequest getPageRequest(PageVO<?> pageVO) {
        PageRequest request = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(),new Sort(Sort.Direction.DESC,"createTime"));
        return request;
    }
    private PageVO<List<HouseLogDto>> buildPageVo(PageVO<List<HouseLogDto>> pageVO, PageImpl<? extends BaseEntity> page) {
        List<? extends BaseEntity> list = page.getContent();
        List<HouseLogDto> dtos = new ArrayList<>();
        for (BaseEntity houseLog : list) {
            HouseLogDto dto = new HouseLogDto();
            BeanUtils.copyProperties(houseLog, dto);
            dtos.add(dto);
        }
        pageVO.setEntity(dtos);
        return pageVO;
    }
}
