package com.qcc.dao;

import com.qcc.domain.Account;
import com.qcc.domain.Repairman;
import org.hibernate.validator.constraints.EAN;

public interface RepairmanDao extends BaseRepository<Repairman> {
    Repairman findRepairmanByAccount(Account account);
}
