package com.task.classifierWorker.service;

import com.task.classifierWorker.model.PurchaseEntry;
import com.task.classifierWorker.repository.PurchaseRepo;
import com.task.classifierWorker.util.TreePathCreator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseVocabularyService {
     private PurchaseRepo purchaseRepo;
     private TreePathCreator treePathCreator;
    public PurchaseVocabularyService(PurchaseRepo purchaseRepo, TreePathCreator treePathCreator) {
        this.purchaseRepo = purchaseRepo;
        this.treePathCreator = treePathCreator;
    }

    public PurchaseEntry getNode(String nodeCode) {
        return purchaseRepo.getNode(nodeCode);
    }

    public List<PurchaseEntry> getSubnodes(String nodeCode) {
        return purchaseRepo.getSubNodes(treePathCreator.createTreePath(nodeCode));
    }
}
