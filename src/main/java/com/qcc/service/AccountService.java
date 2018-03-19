package com.qcc.service;

import com.qcc.annotation.Cache;
import com.qcc.dao.*;
import com.qcc.dao.dto.AccountDto;
import com.qcc.domain.*;
import com.qcc.enums.ROLEEnum;
import com.qcc.utils.CacheMap;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.ResponseVO;
import javafx.beans.property.ReadOnlyDoubleProperty;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Service
@Order(1)
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private RepairmanDao repairmanDao;

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AccountService.class);
    @PostConstruct
    public void injectData() {
      List<Account> list = accountDao.findAll();
      if (list != null && list.size() > 0) {
          return;
      }
      Account li = new Account();
      li.setPassword("1234");
      li.setRole(ROLEEnum.Landlord);
      li.setUserName("landlord");
      accountDao.save(li);
      Account repairman = new Account();
      repairman.setUserName("repairman");
      repairman.setPassword("1234");
      repairman.setRole(ROLEEnum.Repairman);
        accountDao.save(repairman);
        Account tenant = new Account();
        tenant.setRole(ROLEEnum.Tenant);
        tenant.setPassword("1234");
        tenant.setUserName("tenant");
        accountDao.save(tenant);
        tenant.setId(null);
        tenant.setUserName("li");
        accountDao.save(tenant);
    }
    public ResponseVO<Account> register(Account account) {
        if (account== null) {
            return CommUtils.buildReponseVo(false,Constant.OPERAT_FAIL, null);
        }
       Account account1 = accountDao.findAccountByUserNameAndPassword(account.getUserName(), account.getPassword());
        if (account1 != null) {
            return CommUtils.buildReponseVo(false, Constant.EXITES_USER, null);
        }
        accountDao.save(account);
        if (account.getRole().code == ROLEEnum.Landlord.code) {

            Landlord landlord = new Landlord();
            landlord.setAccount(account);
            landlordDao.save(landlord);
        }
        if (account.getRole().code == ROLEEnum.Repairman.code) {
            Repairman repairman = new Repairman();
            repairman.setAccount(account);
            repairmanDao.save(repairman);
        }
        if (account.getRole().code == ROLEEnum.Tenant.code) {
            Tenant tenant = new Tenant();
            tenant.setAccount(account);
            tenantDao.save(tenant);
        }
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, account);
    }

    /**
     * account id != null
     *
     * @param
     * @return
     */
    public ResponseVO<Account> update(Account dto) {
        Account account = new Account();
        if (dto.getId() == null) {
            logger.error("更新错误没有Account id, account={}",dto);
            BeanUtils.copyProperties(dto, account);
            return CommUtils.buildReponseVo(false, Constant.OPERAT_FAIL, account);
        }
        account = accountDao.findOne(dto.getId());
        BeanUtils.copyProperties(dto, account,"createTime","updateTime");
        accountDao.save(account);
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, account);
    }

}
