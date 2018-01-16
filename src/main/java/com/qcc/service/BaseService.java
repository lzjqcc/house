package com.qcc.service;

import com.qcc.domain.BaseEntity;
import com.qcc.utils.ResponseVO;

public interface BaseService<T extends BaseEntity> {
    ResponseVO<T> update(T entity);
}
