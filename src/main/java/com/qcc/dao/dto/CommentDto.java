package com.qcc.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qcc.domain.Account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommentDto implements Serializable {
    private static final long serialVersionUID = 7531458679843605L;
    private String fromAccountName;
    private String toAccountName;
    private Integer toAccountId;
    private Integer score;
    /**
     * 回复哪个评论
     */
    private Integer replayId;
    private Integer landlordId;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<CommentDto> children;

    public List<CommentDto> getChildren() {
        return children;
    }

    public void setChildren(List<CommentDto> children) {
        this.children = children;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public Integer getReplayId() {
        return replayId;
    }

    public void setReplayId(Integer replayId) {
        this.replayId = replayId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }

    private String conversation;
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getFromAccountName() {
        return fromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
