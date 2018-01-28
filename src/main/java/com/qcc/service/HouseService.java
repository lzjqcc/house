package com.qcc.service;

import com.qcc.dao.HouseDao;
import com.qcc.dao.HouseLogDao;
import com.qcc.dao.LandlordDao;
import com.qcc.dao.TenantDao;
import com.qcc.dao.dto.HouseDto;
import com.qcc.dao.dto.HouseLogDto;
import com.qcc.domain.House;
import com.qcc.domain.HouseLog;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import com.sun.org.apache.bcel.internal.generic.LAND;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        pageVO.setCount(page.getTotalPages());
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
            houseDtos.add(dto);
        }
        pageVO.setEntity(houseDtos);
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
                if (house.getAddress() != null) {
                    predicates.add(cb.like(root.get("address").as(String.class), "^" + house.getAddress() + "*"));
                }
                // 面积大于
                if (house.getArea() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("area").as(Integer.class), house.getArea()));
                }
                // 超想
                if (house.getDirection() != null) {
                    predicates.add(cb.equal(root.get("direction").as(String.class), house.getDirection()));
                }
                if (house.getPayMent() != null) {
                }
                if (house.getDecoration() != null) {
                    predicates.add(cb.equal(root.get("decoration").as(String.class), house.getDecoration()));
                }

                Predicate[] pre = new Predicate[predicates.size()];
                ;
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        };
    }
}
