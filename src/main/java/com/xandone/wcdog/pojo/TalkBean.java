package com.xandone.wcdog.pojo;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/7/1 17:02
 * description：
 */
public class TalkBean {
    private String userId;
    private String talk;
    private Date sendTime;
    private String token;
    private String userIcon;
    private String userNick;

    public TalkBean(){}

    public TalkBean(String userId, String talkStr) {
        this.userId = userId;
        this.talk = talkStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
}
