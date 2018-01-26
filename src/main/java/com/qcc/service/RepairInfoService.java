package com.qcc.service;

import com.qcc.dao.*;
import com.qcc.dao.dto.RepairInfoDto;
import com.qcc.dao.dto.RepairInfoOrderDto;
import com.qcc.domain.*;
import com.qcc.enums.RepairInfoEnum;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class RepairInfoService {
    @Autowired
    private RepairInfoDao repairInfoDao;
    @Autowired
    private RepairmanDao repairmanDao;
    @Autowired
    private RepairInfoOrderDao repairInfoOrderDao;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private LandlordDao landlordDao;
    /**
     * 更新 维修状态,当维修状态为：接受维修，就插入一条维修的订单
     *维修人员接受 维修任务
     * @param repairInfoId
     * @param repairInfoEnum
     * @return
     */
    public ResponseVO updateRepairInfoStatus(Integer repairInfoId, int repairInfoEnum, Integer repairmanId) {
        RepairInfo repairInfo = repairInfoDao.findOne(repairInfoId);
        repairInfo.setStatus(repairInfoEnum);
        // 接受维修任务，写入repairInfoOrder
        if (repairInfoEnum == RepairInfoEnum.REPAIRMAN_COMPLETE.code) {
            if (repairmanId == null) {
                return CommUtils.buildReponseVo(false, Constant.OPERAT_FAIL+":没有维修人员",null );
            }
            Repairman repairman = repairmanDao.findOne(repairmanId);
            RepairInfoOrder order = new RepairInfoOrder();
            order.setHouseAddress(repairInfo.getHouse().getAddress());
            order.setLandLordMobile(repairInfo.getLandlord().getAccount().getMobile());
            order.setLandlordName(repairInfo.getLandlord().getAccount().getName());
            order.setRepairPrice(repairInfo.getRepairPrice());
            order.setRepairman(repairman);
            order.setRepairThing(repairInfo.getRepairThing());
            order.setRepairInfoId(repairInfo.getId());
            repairInfoOrderDao.save(order);
        }
        // 维修完成
        if (repairInfoEnum == RepairInfoEnum.REPAIRMAN_COMPLETE.code) {
            RepairInfoOrder order = repairInfoOrderDao.findRepairInfoOrderByRepairInfoId(repairInfo.getId());
            if (order == null) {
                return CommUtils.buildReponseVo(false, Constant.OPERAT_FAIL + ":维修人员没有接受维修任务，不能点击完成", null);
            }
            order.setStatus(repairInfoEnum);
            repairInfoOrderDao.save(order);
        }
        repairInfoDao.save(repairInfo);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, null);
    }

    /**
     * 租客申请维修
     * @return
     */
    public ResponseVO<RepairInfoDto> pubilshRepairInfo(Account tenantAccount, RepairInfoDto dto) {
        RepairInfo repairInfo = new RepairInfo();
        repairInfo.setTenant(tenantDao.findTenantByAccount(tenantAccount));
        repairInfo.setDescribtion(dto.getDescribtion());
        House house = houseDao.findOne(dto.getHouseId());
        repairInfo.setHouse(house);
        repairInfo.setStatus(RepairInfoEnum.TENANT_APPLY.code);
        repairInfo.setLandlord(landlordDao.findLandlordByTenants(repairInfo.getTenant()));
        repairInfo.setRepairThing(dto.getRepairThing());
        if (dto.getImagesURL() != null) {
            Set<Image> images = new HashSet<>();
            List<String> imageURLs = dto.getImagesURL();
            for (String url : imageURLs) {
                Image image = new Image();
                image.setUrl(url);
                images.add(image);
            }
            repairInfo.setImages(images);
        }
        repairInfo = repairInfoDao.save(repairInfo);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, null);
    }
    /**
     * 房东查询自己发布的维修任务
     * @param account
     * @param pageVO
     * @return
     */
    public PageVO<List<RepairInfoDto>> findRepairInfoByLandlord(Account account, PageVO<List<RepairInfoDto>> pageVO, Integer status) {
        PageRequest pageRequest = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(), new Sort(Sort.Direction.DESC, "createTime"));
        PageImpl<RepairInfo> page = (PageImpl<RepairInfo>) repairInfoDao.findRepairInfosByLandlord_AccountAndStatus(account, status,pageRequest);
        buildPageVO(page, pageVO);
        return pageVO;
    }

    /**
     * 查询房东们发布的维修任务
     *
     * @param dto
     * @param pageVo
     * @param orderBy
     * @return
     */
    public PageVO<List<RepairInfoDto>> findRepairInfo(RepairInfoDto dto, PageVO<List<RepairInfoDto>> pageVo, String orderBy) {
        Sort sort = null;
        if (orderBy != null) {
            sort = new Sort(Sort.Direction.DESC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.DESC, "createTime");
        }
        PageImpl<RepairInfo> pageImpl = (PageImpl<RepairInfo>) repairInfoDao.findAll(getWhereCase(dto), new PageRequest(pageVo.getCurrentPage() - 1, pageVo.getSize(), sort));
        buildPageVO(pageImpl, pageVo);
        return pageVo;
    }

    /**
     * 维修人员查看自己的订单
     * @param repairman
     * @param pageVO
     * @return
     */
    public PageVO<List<RepairInfoOrderDto>> findRepairInfoOrderByRepairman(Repairman repairman, PageVO<List<RepairInfoOrderDto>> pageVO) {

        PageImpl<RepairInfoOrder> page = repairInfoOrderDao.findRepairInfoOrderByRepairman(repairman, getPageRequest(pageVO));
        List<RepairInfoOrder> list = page.getContent();
        List<RepairInfoOrderDto> dtos = new ArrayList<>();
        for (RepairInfoOrder order : list) {
            dtos.add(buildRepairInfoOrderDto(order));
        }
        pageVO.setEntity(dtos);
        return pageVO;
    }
    private RepairInfoOrderDto buildRepairInfoOrderDto(RepairInfoOrder order) {
        RepairInfoOrderDto dto = new RepairInfoOrderDto();
        BeanUtils.copyProperties(order, dto);
        return  dto;
    }
    private void buildPageVO(PageImpl<RepairInfo> page, PageVO<List<RepairInfoDto>> pageVO) {
        pageVO.setCount(page.getTotalPages());
        List<RepairInfoDto> dtos = new ArrayList<>();
        List<RepairInfo> list = page.getContent();
        for (RepairInfo repairInfo : list) {
            dtos.add(buildPageInfoDto(repairInfo));
        }
    }
    private PageRequest getPageRequest(PageVO<?> pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(), sort);
        return request;
    }
    private RepairInfoDto buildPageInfoDto(RepairInfo repairInfo) {
        RepairInfoDto dto = new RepairInfoDto();
        dto.setCreateTime(repairInfo.getCreateTime());
        dto.setDescribtion(repairInfo.getDescribtion());
        dto.setHouseAddress(repairInfo.getHouse().getAddress());
        dto.setId(repairInfo.getId());
        dto.setLandlordId(repairInfo.getLandlord().getId());
        dto.setLandlordMobile(repairInfo.getLandlord().getAccount().getMobile());
        dto.setLandlordName(repairInfo.getLandlord().getAccount().getName());
        dto.setRepairThing(repairInfo.getRepairThing());
        dto.setRepairTime(repairInfo.getRepairTime());
        dto.setRepairPrice(repairInfo.getRepairPrice());
        Set<Image> images = repairInfo.getImages();
        List<String> list = new ArrayList<>();
        for (Image image : images) {
            list.add(image.getUrl());
        }
        dto.setImagesURL(list);
        return dto;
    }

    /**
     *
     */
    private Specification<RepairInfo> getWhereCase(final RepairInfoDto dto) {
        return new Specification<RepairInfo>() {
            @Override
            public Predicate toPredicate(Root<RepairInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (dto.getGtPrice() != null) {
                    if (dto.getRepairPrice() != null && dto.getGtPrice()) {
                        predicates.add(cb.greaterThanOrEqualTo(root.get("repairPrice").as(Integer.class), dto.getRepairPrice()));
                    }
                    if (dto.getRepairPrice() != null && !dto.getGtPrice()) {
                        predicates.add(cb.lt(root.get("repairPrice").as(Integer.class), dto.getRepairPrice()));
                    }
                }
                if (dto.getCreateTime() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), dto.getCreateTime()));
                }
                Predicate[] pre = new Predicate[predicates.size()];
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        };
    }
}
