package com.task.classifierWorker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PurchaseEntry {
    @JsonIgnore
    private Integer id;

    private String purchaseCode;

    private String title;

    @JsonIgnore
    private String treePath;

    public PurchaseEntry() {
    }

    public PurchaseEntry(String purchaseCode, String name, String treePath) {
        this.purchaseCode = purchaseCode;
        this.title = name;
        this.treePath = treePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }
}
