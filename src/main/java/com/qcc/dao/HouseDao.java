package com.qcc.dao;

import com.qcc.domain.House;
import com.qcc.domain.Landlord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseDao extends BaseRepository<House> {
    Page<House> findHousesByLandlord(Landlord landlord, Pageable pageable);
    @Query("select distinct new com.qcc.domain.House( h.address,h.hire, h.direction) from House as h group by h.address,h.hire,h.decoration order by h.id")
    List<House> findTableHead();
    @Query("select max(price) from House")
    Integer findMaxPrice();
    @Query("select min(price) from House")
    Integer findMinxPrice();
}
