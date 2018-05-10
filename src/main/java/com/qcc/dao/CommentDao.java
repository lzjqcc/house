package com.qcc.dao;

import com.qcc.domain.Account;
import com.qcc.domain.Comment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentDao extends BaseRepository<Comment> {
    List<Comment> findByLandlordId(Integer landlordId);

    List<Comment> findByToAccount_Id(Integer toAccountId, Sort sort);
}
