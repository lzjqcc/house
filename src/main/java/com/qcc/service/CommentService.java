package com.qcc.service;

import com.alibaba.druid.util.StringUtils;
import com.qcc.dao.AccountDao;
import com.qcc.dao.CommentDao;
import com.qcc.dao.dto.CommentDto;
import com.qcc.domain.Account;
import com.qcc.domain.Comment;
import com.qcc.utils.CommUtils;
import com.qcc.utils.Constant;
import com.qcc.utils.PageVO;
import com.qcc.utils.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
    AccountDao accountDao;
    /**
     * 提交评论
     * @param currentAccount
     * @param commentDto
     * @return
     */
    public ResponseVO pushComment(Account currentAccount, CommentDto commentDto) {
        if (StringUtils.isEmpty(commentDto.getConversation()) || commentDto.getToAccountId() == null || commentDto.getHouseId() == null) {
            return CommUtils.buildReponseVo(false, Constant.OPERAT_FAIL, null);
        }
        Account toAccount = accountDao.findOne(commentDto.getToAccountId());
        Comment comment = new Comment();
        comment.setCurrentAccount(currentAccount);
        comment.setFromAccount(currentAccount);
        comment.setToAccount(toAccount);
        comment.setConversation(commentDto.getConversation());
        if (commentDto.getReplayId() == null) {
            comment.setReplayId(0);
        }
        comment.setHouseId(commentDto.getHouseId());
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, null);
    }

    /**
     * 查看评论
     * @param houseId
     * @return
     */
    public PageVO pullComment(Integer houseId) {
        List<Comment> list = commentDao.findByHouseId(houseId);

        return null;
    }
}
