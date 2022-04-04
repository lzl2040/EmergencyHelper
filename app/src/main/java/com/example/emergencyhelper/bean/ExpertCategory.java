package com.example.emergencyhelper.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家的分类ID
 * author ： yxm521
 * time    ： 2022/4/4
 */
public class ExpertCategory {
    private int categoryId;
    private String categoryName;
    private List<Expert> experts = new ArrayList<>();

    public ExpertCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Expert> getExperts() {
        return experts;
    }

    public void setExperts(List<Expert> experts) {
        this.experts = experts;
    }
}
