package com.qcc.service;

import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.dao.TenantDao;
import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.Account;
import com.qcc.domain.House;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
     * 租客退租
     * @param tenantId
     * @param
     * @return
     */
    public ResponseVO deleteHouseTenantRelation(Integer tenantId) {
        Tenant tenant = tenantDao.findOne(tenantId);
        tenant.setHouse(null);
        tenant.setLandlord(null);
        tenantDao.save(tenant);
        return CommUtils.buildReponseVo(true,Constant.OPERAT_SUCCESS, null);
    }

    /**
     * 查询该房子下的租客
     * @param houseId
     * @param pageRequest
     * @return
     */
    public ResponseVO<Map<Integer,TenantDto>>  findHouseTenants(Integer houseId, PageRequest pageRequest) {
        House house = new House();
        house.setId(houseId);
        List<Tenant> list = tenantDao.findTenantsByHouse(house, pageRequest);
        return CommUtils.buildReponseVo(true,Constant.OPERAT_SUCCESS, tenantDtoMap(list));
    }
    /**
     *查询房东下的租客
     * @param landLordId 房东id
     * @return
     */
    public ResponseVO<Map<Integer,TenantDto>> findLandlordTenants(Integer landLordId, PageRequest pageRequest) {
        Landlord landlord = new Landlord();
        landlord.setId(landLordId);

        List<Tenant> list = tenantDao.findTenantsByLandlord(landlord, pageRequest);

        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS,tenantDtoMap(list));
    }
    private Map<Integer,TenantDto> tenantDtoMap(List<Tenant> list) {
        Map<Integer, TenantDto> map = new LinkedHashMap<>();
        for (Tenant tenant : list) {
            map.put(tenant.getId(), buildTenantDto(tenant.getAccount(), tenant));
        }
        return map;
    }
    private TenantDto buildTenantDto(Account account, Tenant tenant) {
        TenantDto dto = new TenantDto();
        BeanUtils.copyProperties(account,dto);
        dto.setAccountId(account.getId());
        dto.setTenantId(tenant.getId());
        return dto;
    }
}
