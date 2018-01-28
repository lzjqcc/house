package com.qcc.service;

import com.qcc.dao.AccountDao;
import com.qcc.dao.RepairInfoDao;
import com.qcc.dao.RepairmanDao;
import com.qcc.dao.dto.RepairInfoDto;
import com.qcc.domain.Account;
import com.qcc.domain.RepairInfo;
import com.qcc.domain.Repairman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RepairmanService {
    @Autowired
    private RepairmanDao repairmanDao;

    @Autowired
    private RepairInfoDao repairInfoDao;
    @Autowired
    private AccountDao accountDao;
    @PostConstruct
    public void inject() {
        List<Repairman> list = repairmanDao.findAll();
        if (list.size() > 0) {
            return;
        }
        Account account = accountDao.findOne(2);
        Repairman repairman = new Repairman();
        repairman.setAccount(account);
        repairmanDao.save(repairman);
    }
    public Repairman findRepairman(Account account) {
        return repairmanDao.findRepairmanByAccount(account);
    }

}
