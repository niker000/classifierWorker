package com.task.classifierWorker.service;

import com.task.classifierWorker.exception.NoSuchEntryException;
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
        if (!purchaseRepo.isExists(nodeCode)) {
            throw new NoSuchEntryException("There is no classifier with this code.");
        }
        return purchaseRepo.getNode(nodeCode);
    }

    public List<PurchaseEntry> getSubnodes(String nodeCode) {
        List<PurchaseEntry> purchaseEntries;
        if(nodeCode.equals("")) {
            purchaseEntries = purchaseRepo.getHighestEntries();
        } else {
            purchaseEntries = purchaseRepo.getSubNodes(treePathCreator.createTreePath(nodeCode));
        }

        if (purchaseEntries.isEmpty()) {
            throw new NoSuchEntryException("This classifier has no nodes");
        }

        return purchaseEntries;
    }
}
