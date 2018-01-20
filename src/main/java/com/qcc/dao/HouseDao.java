package com.qcc.dao;

import com.qcc.domain.House;
import com.qcc.domain.Landlord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface HouseDao extends BaseRepository<House> {
    Page<House> findHousesByLandlord(Landlord landlord, org.springframework.data.domain.Pageable pageable);
}
