package com.example.emergencyhelper.entity;

import java.io.Serializable;

public class MessageUserEntity implements Serializable {
    private int header;
    private String name;
    private String content;
    private String time;

    public MessageUserEntity(int header, String name, String content, String time){
        this.content=content;
        this.name=name;
        this.header=header;
        this.time=time;
    }
    public int getHeader(){
        return header;
    }
    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
    public String getTime(){
        return time;
    }
}
