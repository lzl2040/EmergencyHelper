package com.example.emergencyhelper.entity;

import java.util.List;

/**
 * 交流的实体类
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class Communication {
    //交流的聊天内容
    private List<Message> messages;
    //发起聊天的人
    private User startContactUser;
    //聊天的人
    private User communicateUser;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getStartContactUser() {
        return startContactUser;
    }

    public void setStartContactUser(User startContactUser) {
        this.startContactUser = startContactUser;
    }

    public User getCommunicateUser() {
        return communicateUser;
    }

    public void setCommunicateUser(User communicateUser) {
        this.communicateUser = communicateUser;
    }
}
