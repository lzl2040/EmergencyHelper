package com.example.emergencyhelper.bean;

/**
 * 专家类
 * author ： yxm521
 * time    ： 2022/4/4
 */
public class Expert {
    private int expertId;
    private String headerUrl;
    private String name;
    private String phone;
    private String area;
    private int receiveNum;
    private ExpertCategory category;

    public Expert(String name, String phone, String area, int receiveNum, ExpertCategory category) {
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.receiveNum = receiveNum;
        this.category = category;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public ExpertCategory getCategory() {
        return category;
    }

    public void setCategory(ExpertCategory category) {
        this.category = category;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }
}
