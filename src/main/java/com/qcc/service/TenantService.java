package com.qcc.service;

import com.qcc.dao.*;
import com.qcc.dao.dto.LandlordDto;
import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.*;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TenantService {
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private HouseOrderDao houseOrderDao;

    @PostConstruct
    public void inject() {
        List<Tenant> list = tenantDao.findAll();
        if (list.size() > 0) {
            return;
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountDao.findOne(3), account);
        Tenant tenant = new Tenant();
        tenant.setAccount(account);
        tenantDao.save(tenant);
        tenant.setId(null);
        tenant.setAccount(accountDao.findOne(4));
        tenantDao.save(tenant);
    }

    /**
     * 租客租房
     *
     * @return
     */
    public ResponseVO tenantHouse(Integer houseId, Tenant tenant) {
        House house = houseDao.findOne(houseId);
        tenant.setHouse(house);
        tenant.setLandlord(house.getLandlord());
        HouseOrder order = new HouseOrder();
        BeanUtils.copyProperties(house, order);
        order.setTenant(tenant);
        houseOrderDao.save(order);
        tenantDao.save(tenant);
        house.setStatus(true);
        houseDao.save(house);
        return CommUtils.buildReponseVo(true, "操作成功", null);
    }

    /**
     * 租客退租
     *
     * @param tenantId
     * @param
     * @return
     */
    public ResponseVO deleteHouseTenantRelation(Integer tenantId, Integer houseId) {
        Tenant tenant = tenantDao.findOne(tenantId);
        if (tenant.getHouse() == null && tenant.getLandlord() == null) {
            return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, null);
        }

        tenant.setHouse(null);
        tenant.setLandlord(null);
        tenantDao.save(tenant);
        House house = houseDao.findOne(houseId);
        if (house.getTenants().size() == 0) {
            house.setStatus(false);
            houseDao.save(house);
        }
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, null);
    }

    public ResponseVO findMyLandlord() {
        Landlord landlord = landlordDao.findLandlordByTenants(tenantDao.findTenantByAccount(CommUtils.getCurrentAccount()));

        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, buildLandlordDto(landlord));
    }

    /**
     * 查询该房子下的租客
     *
     * @param houseId
     * @param pageRequest
     * @return
     */
    public ResponseVO<Map<Integer, TenantDto>> findHouseTenants(Integer houseId) {

        House house = new House();
        house.setId(houseId);
        List<Tenant> list = tenantDao.findTenantsByHouse(house);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, tenantDtoMap(list));
    }

    /**
     * 查询房东下的租客
     *
     * @param landLordId 房东id
     * @return
     */
    public PageVO<List<TenantDto>> findLandlordTenants(Integer landLordId, PageVO<List<TenantDto>> pageVO) {
        Landlord landlord = new Landlord();
        landlord.setId(landLordId);
        PageRequest pageRequest = new PageRequest(pageVO.getCurrentPage() - 1, pageVO.getSize());
        PageImpl<Tenant> pageImpl = (PageImpl<Tenant>) tenantDao.findTenantsByLandlord(landlord, pageRequest);
        List<Tenant> list = pageImpl.getContent();
        List<TenantDto> dtos = Lists.newArrayList();
        for (Tenant tenant : list) {
            dtos.add(buildTenantDto(tenant.getAccount(), tenant));
        }
        pageVO.setEntity(dtos);
        return pageVO;
    }

    private Map<Integer, TenantDto> tenantDtoMap(List<Tenant> list) {
        Map<Integer, TenantDto> map = new LinkedHashMap<>();
        for (Tenant tenant : list) {
            map.put(tenant.getId(), buildTenantDto(tenant.getAccount(), tenant));
        }
        return map;
    }

    private LandlordDto buildLandlordDto(Landlord landlord) {
        LandlordDto landlordDto = new LandlordDto();
        landlordDto.setLandlordId(landlord.getId());
        landlordDto.setAccountId(landlord.getAccount().getId());
        landlordDto.setAge(landlord.getAccount().getAge());
        landlordDto.setMobile(landlord.getAccount().getMobile());
        landlordDto.setGender(landlord.getAccount().getGender());
        landlordDto.setDescription(landlord.getAccount().getDescription());
        landlordDto.setJob(landlord.getAccount().getJob());
        landlordDto.setName(landlord.getAccount().getName());
        return landlordDto;
    }

    private TenantDto buildTenantDto(Account account, Tenant tenant) {
        TenantDto dto = new TenantDto();
        BeanUtils.copyProperties(account, dto);
        dto.setAccountId(account.getId());
        dto.setTenantId(tenant.getId());
        return dto;
    }
}
