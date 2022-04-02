package com.example.emergencyhelper.bean;


import java.io.Serializable;
import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class Communicate implements Serializable {
    //聊天ID
    private int communicateId;
    //发起聊天的用户
    private User startUser;
    //接受聊天的用户
    private User acceptUser;
    //聊天日期
    private String communicateDate;
    //消息
    private List<Message> messages;

    public int getCommunicateId() {
        return communicateId;
    }

    public void setCommunicateId(int communicateId) {
        this.communicateId = communicateId;
    }

    public User getStartUser() {
        return startUser;
    }

    public void setStartUser(User startUser) {
        this.startUser = startUser;
    }

    public User getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(User acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getCommunicateDate() {
        return communicateDate;
    }

    public void setCommunicateDate(String communicateDate) {
        this.communicateDate = communicateDate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
