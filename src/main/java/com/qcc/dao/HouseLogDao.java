package com.qcc.dao;

import com.qcc.domain.House;
import com.qcc.domain.HouseLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseLogDao extends BaseRepository<HouseLog>{
    Page<HouseLog> findHouseLogsByHouse_Id(Integer houseId, Pageable pageable);
}
