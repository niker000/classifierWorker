package com.task.classifierWorker.repository;

import com.task.classifierWorker.model.PurchaseEntry;

import java.util.List;

public interface PurchaseRepo {
    void updateDb(List<PurchaseEntry> data);

    PurchaseEntry getNode(String code);

    List<PurchaseEntry> getSubNodes(String node);
}
