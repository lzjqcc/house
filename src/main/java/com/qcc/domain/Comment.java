package com.qcc.domain;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "tb_comment")
public class Comment extends BaseEntity{
    @ManyToOne(cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY,targetEntity = Account.class)
    @JoinColumn(name = "current_account_id")
    private Account currentAccount;
    @ManyToOne(cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY,targetEntity = Account.class)
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;
    @ManyToOne(cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY,targetEntity = Account.class)
    @JoinColumn(name = "to_account_id")
    private Account toAccount;
    // 评论
    private String conversation;
    private Integer score;
    /**
     * 回复哪个评论，0为直接评论
     */
    private Integer replayId;
    private Integer landlordId;
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }
}
