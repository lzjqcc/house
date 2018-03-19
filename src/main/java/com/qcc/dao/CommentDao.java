package com.qcc.dao;

import com.qcc.domain.Comment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentDao extends BaseRepository<Comment> {
    List<Comment> findByHouseId(Integer houseId);
}
