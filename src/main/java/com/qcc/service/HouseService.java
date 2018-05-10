package com.qcc.service;

import com.alibaba.druid.util.StringUtils;
import com.qcc.annotation.Cache;
import com.qcc.controller.ImageController;
import com.qcc.dao.*;
import com.qcc.dao.dto.HouseDto;
import com.qcc.dao.dto.HouseLogDto;
import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.*;
import com.qcc.enums.TableEnum;
import com.qcc.utils.*;
import com.sun.org.apache.bcel.internal.generic.LAND;
import javafx.scene.control.Tab;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.regex.Pattern;

@Service
@DependsOn(value = {"landlordService","tenantService"})
public class HouseService {
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private HouseLogDao houseLogDao;

    @Cache(space = "image")
    private CacheMap<List<ImageController.ImageFile>> cacheMap;
    @PostConstruct
    public void inject() {
        if (houseDao.findAll().size() > 0) {

            return;
        }
        Landlord landlord = landlordDao.findOne(1);
        House house = new House();
        house.setAddress("江西省上饶市余干县");
        house.setDecoration("简修");
        house.setLandlord(landlord);
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(tenantDao.findOne(1), tenant);
        house.setTenants(Sets.newHashSet(Lists.newArrayList(tenant)));
        houseDao.save(house);
    }
    public ResponseVO<List<HouseDto>> findBastHouse() {
        Pageable pageable = new PageRequest(0, 5 , new Sort(Sort.Direction.DESC,"click"));
        List<House> list = houseDao.findAll(pageable).getContent();
        List<HouseDto> houseDtos = new ArrayList<>();
        list.stream().forEach(t -> {
            HouseDto dto = new HouseDto();
            BeanUtils.copyProperties(t, dto);
            houseDtos.add(dto);
        });
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, houseDtos );
    }

    public ResponseVO publishHouse(House house, Account account) {
        Landlord landlord = landlordDao.findLandlordByAccount_Id(account.getId());
        house.setLandlord(landlord);
        Set<Image> images = new LinkedHashSet<>();
        List<ImageController.ImageFile> imageURLs = cacheMap.get(account.getId()+"");
        if (imageURLs != null && imageURLs.size() > 0) {
            for (ImageController.ImageFile imageURL : imageURLs) {
                Image image = new Image();
                image.setUrl(imageURL.getUrl());
                images.add(image);
                cacheMap.remove(account.getId()+"");
            }
            house.setImages(images);

        }
        house.setStatus(false);
        houseDao.save(house);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, null);
    }
    /**
     * 房东查看有几套贩子
     * @param landlord
     * @param pageVO
     * @return
     */
    public PageVO<List<HouseDto>> findHouseByLandLoard(Landlord landlord, PageVO pageVO) {
        PageRequest pageRequest = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(), new Sort(Sort.Direction.DESC, "createTime"));
        PageImpl<House> page = (PageImpl<House>) houseDao.findHousesByLandlord(landlord, pageRequest);
        buildPageVO(pageVO, page);
        return pageVO;

    }

    /**
     * 根据组合的条件查询房子
     *
     * @param houseDto
     * @param pageVO
     * @return
     */
    public PageVO<List<HouseDto>> findHouses(HouseDto houseDto, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(), sort);
        PageImpl<House> page = (PageImpl<House>) houseDao.findAll(getWhereCase(houseDto), request);
        buildPageVO(pageVO, page);
        return pageVO;
    }
    public ResponseVO<HouseDto> clickHouse(Integer houseId) {
        House house = houseDao.findOne(houseId);
        house.setClick(house.getClick() == null ? 1 : house.getClick() + 1);
        houseDao.save(house);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, buildHouseDto(house));
    }
    private HouseDto buildHouseDto(House house) {
        HouseDto houseDto = new HouseDto();
        BeanUtils.copyProperties(house, houseDto);
        houseDto.setLandLordAge(house.getLandlord().getAccount().getAge());
        houseDto.setLandLordGender(house.getLandlord().getAccount().getGender());
        houseDto.setLandLordMobile(house.getLandlord().getAccount().getMobile());
        houseDto.setLandLordName(house.getLandlord().getAccount().getName());
        houseDto.setLandLordDescribtion(house.getLandlord().getAccount().getDescription());
        houseDto.setLandLordAccountId(house.getLandlord().getAccount().getId());
        houseDto.setLandLordId(house.getLandlord().getId());
        return houseDto;
    }
    /**
     * 查询该房子 租费的信息
     * @param houseId
     * @param pageVO
     * @return
     */
    public PageVO<List<HouseLogDto>> findHouseLogs(Integer houseId,PageVO pageVO) {
        PageRequest request = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(), new Sort(Sort.Direction.DESC, "createTime"));
        PageImpl<HouseLog> page = (PageImpl<HouseLog>) houseLogDao.findHouseLogsByHouse_Id(houseId,request);
        List<HouseLog> list = page.getContent();
        List<HouseLogDto> logs = Lists.newArrayList();
        for (HouseLog log : list) {
            HouseLogDto dto = new HouseLogDto();
            BeanUtils.copyProperties(log,dto);
            logs.add(dto);
        }
        pageVO.setEntity(logs);
        pageVO.setCount(page.getTotalPages());
        return pageVO;
    }
    private void buildPageVO(PageVO pageVO, PageImpl<House> page) {
        pageVO.setSize(page.getSize());
        pageVO.setCount((int) page.getTotalElements());
        List<House> list = page.getContent();
        List<HouseDto> houseDtos = new ArrayList<>();
        for (House house : list) {
            HouseDto dto = new HouseDto();
            BeanUtils.copyProperties(house, dto);
            dto.setLandLordAge(house.getLandlord().getAccount().getAge());
            dto.setLandLordDescribtion(house.getLandlord().getAccount().getDescription());
            dto.setLandLordMobile(house.getLandlord().getAccount().getMobile());
            dto.setLandLordName(house.getLandlord().getAccount().getName());
            dto.setLandLordGender(house.getLandlord().getAccount().getGender());
            if (CollectionUtils.isNotEmpty(house.getTenants())) {
                Set<TenantDto> set = new HashSet<>();
                for (Tenant tenant : house.getTenants()) {
                    TenantDto tenantDto = new TenantDto();
                    tenantDto.setTenantId(tenant.getId());
                    tenantDto.setAccountId(tenant.getAccount().getId());
                    tenantDto.setUserName(tenant.getAccount().getUserName());
                    tenantDto.setName(tenant.getAccount().getName());
                    tenantDto.setAge(tenant.getAccount().getAge());
                    tenantDto.setGender(tenant.getAccount().getGender());
                    tenantDto.setMobile(tenant.getAccount().getMobile());
                    set.add(tenantDto);
                }
                dto.setTenantDtos(set);
            }
            houseDtos.add(dto);
        }
        pageVO.setEntity(houseDtos);
    }
    public ResponseVO getTableHead() {
        List<House> list = houseDao.findTableHead();
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> address = new HashSet<>();
        Set<String> hire = new HashSet<>();
        Set<String> direction = new HashSet<>();
        Set<String> price = new HashSet<>();
        price.add(houseDao.findMaxPrice() + "");
        price.add(houseDao.findMinxPrice() + "");
        for (House house: list) {
            if (house.getAddress() != null) {
                address.add(house.getAddress());
            }
            if (house.getHire() != null) {
                hire.add(house.getHire()? "整租" : "合租");
            }
            if (house.getDecoration() != null) {
                direction.add(house.getDecoration());
            }
        }
        map.put(TableEnum.ADDRESS.value, address);
        map.put(TableEnum.HIRE.value, hire);
        map.put(TableEnum.DIRECTION.value, direction);
        map.put(TableEnum.PRICE.value, price);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, map);
    }
    /**
     * 组合查询条件
     *
     * @param house
     * @return
     */
    private Specification getWhereCase(final HouseDto house) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (house.getCreateTime() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createTime").as(Date.class), house.getCreateTime()));
                }
                //
                if (!StringUtils.isEmpty(house.getAddress())) {
                    predicates.add(cb.like(root.get("address").as(String.class), "%"+house.getAddress()+"%"));
                }
                // 面积大于
                if (house.getArea() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("area").as(Integer.class), house.getArea()));
                }
                // 超想
                if (!StringUtils.isEmpty(house.getDirection())) {
                    predicates.add(cb.equal(root.get("direction").as(String.class), house.getDirection()));
                }
                if (house.getPayMent() != null) {
                }

                if (house.getMinPrice() != null && house.getMaxPrice() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("price").as(Integer.class), house.getMinPrice()));
                    predicates.add(cb.lessThanOrEqualTo(root.get("price").as(Integer.class), house.getMaxPrice()));
                }

                if (house.getMaxPrice() != null && house.getMinPrice() == null) {
                    predicates.add(cb.lessThan(root.get("price"), house.getMaxPrice()));
                }
                Predicate[] pre = new Predicate[predicates.size()];
                ;
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        };
    }
}
