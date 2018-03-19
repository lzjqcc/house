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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.partitioningBy;

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
    public ResponseVO<List<CommentDto>> pullComment(Integer houseId) {

        List<Comment> list = commentDao.findByHouseId(houseId);
        Map<Boolean, List<Comment>> partitioned =
                list.stream().collect(partitioningBy(e -> e.getReplayId() > 0));
        List<Comment> children = partitioned.get(true);
        List<Comment> commentList = partitioned.get(false);
        List<CommentDto> parents = new ArrayList<>();
        for (Comment comment : commentList) {
                CommentDto parent = buildCommentDto(comment);
                CommentDto temp = parent;
                List<CommentDto> commentDtos = new ArrayList<>();
                Iterator<Comment> iterator = children.iterator();
                while (iterator.hasNext()) {
                    Comment next = iterator.next();
                    if (next.getReplayId().intValue() == temp.getId().intValue()) {
                        commentDtos.add(buildCommentDto(next));
                        iterator.remove();
                        temp = buildCommentDto(next);
                    }

                }
                parent.setChildren(commentDtos);
                parents.add(parent);
            }
        return CommUtils.buildReponseVo(true, Constant.OPERAT_SUCCESS, parents);
    }
    private CommentDto buildCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setConversation(comment.getConversation());
        commentDto.setToAccountId(comment.getToAccount().getId());
        commentDto.setToAccountName(comment.getToAccount().getName());
        commentDto.setCreateTime(comment.getCreateTime());
        commentDto.setFromAccountName(comment.getFromAccount().getName());
        commentDto.setReplayId(comment.getReplayId());
        return commentDto;
    }
}
