package com.qcc.service;

import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.domain.Account;
import com.qcc.domain.Landlord;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LandlordService {
    /*@PostConstruct
    public void inject(){
        List<Landlord> list = landlordDao.findAll();
        if (list == null || list.size() == 0) {
            return;
        }
        Landlord landlord = new Landlord();
        Account account = new Account();
        BeanUtils.copyProperties(accountDao.findOne(1), account);
        landlord.setAccount(account);

        landlordDao.save(landlord);
    }*/
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private AccountDao accountDao;
}
