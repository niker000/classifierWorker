package com.task.classifierWorker.controller;

import com.task.classifierWorker.model.PurchaseEntry;
import com.task.classifierWorker.service.DbUpdaterService;
import com.task.classifierWorker.service.PurchaseVocabularyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/app")
public class PurchaseController {
    private DbUpdaterService dbUpdaterService;
    private PurchaseVocabularyService purchaseService;

    public PurchaseController(DbUpdaterService dbUpdaterService, PurchaseVocabularyService purchaseService) {
        this.dbUpdaterService = dbUpdaterService;
        this.purchaseService = purchaseService;
    }

    @GetMapping("/updatedb")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void update() throws IOException {

        dbUpdaterService.update();
    }

    @GetMapping("/node")
    public PurchaseEntry getByCode(@RequestParam String code) {
        return purchaseService.getNode(code);
    }

    @GetMapping("/nodes")
    public List<PurchaseEntry> getSubnodes(@RequestParam String code) {
        return purchaseService.getSubnodes(code);
    }
}
