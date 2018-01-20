package com.qcc.service;

import com.qcc.dao.AccountDao;
import com.qcc.dao.LandlordDao;
import com.qcc.domain.Account;
import com.qcc.domain.Landlord;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import java.util.List;

@Service
@Order(2)
public class LandlordService {
    @PostConstruct
    public void inject(){
        List<Landlord> list = landlordDao.findAll();
        if (list.size() > 0) {
            return;
        }
        Landlord landlord = new Landlord();
        Account account = accountDao.findOne(1);
        landlord.setAccount(account);

        landlordDao.save(landlord);
    }
    @Autowired
    private LandlordDao landlordDao;
    @Autowired
    private AccountDao accountDao;
}
