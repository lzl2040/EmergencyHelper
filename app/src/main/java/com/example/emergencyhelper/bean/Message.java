package com.example.emergencyhelper.bean;


import java.io.Serializable;

/**
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class Message implements Serializable {
    //消息ID
    private int msgId;
    //消息内容
    private String content;
    //消息日期
    private String msgDate;
    //消息类型
    private int msgType;
    //发消息的用户
    private User sendUser;

    public Message(String content, String msgDate, int msgType, User sendUser) {
        this.content = content;
        this.msgDate = msgDate;
        this.msgType = msgType;
        this.sendUser = sendUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(String msgDate) {
        this.msgDate = msgDate;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }
}
