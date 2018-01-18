package com.qcc.service;

import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.dao.TenantDao;
import com.qcc.dao.dto.TenantDto;
import com.qcc.domain.Account;
import com.qcc.domain.Landlord;
import com.qcc.domain.Tenant;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.ResponseVO;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.assertj.core.util.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        tenantDao.save(tenant);
    }
    public ResponseVO<Map<Integer,TenantDto>> findTenants(Integer id) {
        Landlord landlord = landlordDao.findOne(id);
        Set<Tenant> set = landlord.getTenants();
        Map<Integer, TenantDto> map = new HashMap<Integer, TenantDto>();
        for (Tenant tenant : set) {
            map.put(tenant.getId(), buildTenantDto(tenant.getAccount(), tenant));
        }
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS,map);
    }
    private TenantDto buildTenantDto(Account account, Tenant tenant) {
        TenantDto dto = new TenantDto();
        BeanUtils.copyProperties(account,dto);
        dto.setAccountId(account.getId());
        dto.setTenantId(tenant.getId());
        return dto;
    }
}
