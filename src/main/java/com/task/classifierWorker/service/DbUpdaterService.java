package com.task.classifierWorker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.classifierWorker.util.TreePathCreator;
import com.task.classifierWorker.model.PurchaseEntry;
import com.task.classifierWorker.repository.PurchaseRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class DbUpdaterService {
    private final String DATA_SOURCE_URL = "https://prozorroukr.github.io/standards/classifiers/dk021_uk.json";
    private PurchaseRepo purchaseRepo;

    public DbUpdaterService(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }

    @Transactional
    public void update() throws IOException {
        String json = getDataForUpdate().getBody();
        Map<String, String> data = getMapFromJson(json);
        ArrayList<PurchaseEntry> entryList = new ArrayList<>();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            entryList.add(createPurchaseEntry(entry.getKey(), entry.getValue()));
        }
        purchaseRepo.updateDb(entryList);
    }

    private ResponseEntity<String> getDataForUpdate() {
        RestTemplate template = new RestTemplate();

        return template.getForEntity(DATA_SOURCE_URL, String.class);
    }

    private Map<String, String> getMapFromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        });
    }

    private PurchaseEntry createPurchaseEntry(String code, String transcription) {
        TreePathCreator treePath = new TreePathCreator();

        return new PurchaseEntry(code, transcription, treePath.createTreePath(code));
    }
}
