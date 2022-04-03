package com.example.emergencyhelper.entity;

/**
 * author ： yxm521
 * time    ： 2022/4/3
 */
public class SortItemEntity {
    private int imgId;
    private int nameId;

    public SortItemEntity(int imgId, int nameId) {
        this.imgId = imgId;
        this.nameId = nameId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }
}
