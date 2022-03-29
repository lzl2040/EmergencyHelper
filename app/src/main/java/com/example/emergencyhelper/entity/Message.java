package com.example.emergencyhelper.entity;

/**
 * 发送消息时候的实体类
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class Message {
    //头像ID
    private int imgId;
    //消息内容
    private String content;
    //消息类别,是自己发送的消息还是接收的消息
    private int msgType;

    public Message(int imgId, String content, int msgType) {
        this.imgId = imgId;
        this.content = content;
        this.msgType = msgType;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
