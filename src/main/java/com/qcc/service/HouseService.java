package com.qcc.service;

import com.qcc.dao.HouseDao;
import com.qcc.dao.LandlordDao;
import com.qcc.dao.TenantDao;
import com.qcc.dao.dto.HouseDto;
import com.qcc.domain.House;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import com.sun.org.apache.bcel.internal.generic.LAND;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
public class HouseService {
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private TenantDao tenantDao;
    @PostConstruct
    public void inject(){
        if (houseDao.findAll().size() > 0) {
            return;
        }
        Landlord landlord = new Landlord();
        BeanUtils.copyProperties(landlordDao.findOne(1), landlord);
        House house = new House();
        house.setAddress("江西省上饶市余干县");
        house.setDecoration("简修");
        house.setLandlord(landlord);
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(tenantDao.findOne(1), tenant);
        house.setTenants(Sets.newHashSet(Lists.newArrayList(tenant)));
        houseDao.save(house);
    }
    public PageVO findHouses(HouseDto houseDto, PageVO pageVO) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        PageRequest request = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize(),sort);
        pageVO.setCount((int) houseDao.count(getWhereCase(houseDto)));
        pageVO.setEntity(houseDao.findAll(getWhereCase(houseDto), request));
        return pageVO;
    }
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
                    predicates.add(cb.like(root.get("address").as(String.class), "^"+house.getAddress()+"*"));
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
                Predicate[] pre = new Predicate[predicates.size()];
                ;
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        };
    }
}
