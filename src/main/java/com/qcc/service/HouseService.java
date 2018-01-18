package com.qcc.service;

import com.qcc.dao.HouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HouseService {
    @Autowired
    private HouseDao houseDao;
    @PostConstruct
    public void inject(){

    }
}
