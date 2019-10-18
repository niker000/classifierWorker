package com.task.classifierWorker.repository;

import com.task.classifierWorker.mapper.PurchaseEntryMapper;
import com.task.classifierWorker.model.PurchaseEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PurchaseRepoImpl implements PurchaseRepo {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private PurchaseEntryMapper mapper;

    public PurchaseRepoImpl(JdbcTemplate jdbcTemplate, PurchaseEntryMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("procurement_vocabulary")
                .usingGeneratedKeyColumns("id");
        this.mapper = mapper;
    }

    public void updateDb(List<PurchaseEntry> data) {
        List<Object[]> batch = new ArrayList<>();
        Object[] parameters;
        for (PurchaseEntry purchaseEntry : data) {
            parameters = new Object[10];
            parameters[0] = purchaseEntry.getPurchaseCode();
            parameters[1] = purchaseEntry.getTitle();
            parameters[2] = purchaseEntry.getPurchaseCode();
            parameters[3] = purchaseEntry.getTreePath();
            parameters[4] = purchaseEntry.getPurchaseCode();
            parameters[5] = purchaseEntry.getTitle();
            parameters[6] = purchaseEntry.getTreePath();
            parameters[7] = purchaseEntry.getTitle();
            parameters[8] = purchaseEntry.getPurchaseCode();
            parameters[9] = purchaseEntry.getTreePath();

            batch.add(parameters);
        }
//        String sql = "INSERT INTO procurement_vocabulary (title, code, tree_path)" +
//            "VALUES(?, ?, CAST(? as ltree)) ";

//TODO захуярить это сраное обновление бд
        String sql = "SELECT *, CASE " +
                "WHEN NOT EXISTS (SELECT code FROM procurement_vocabulary WHERE code = ?) THEN " +
                    "INSERT INTO procurement_vocabulary (title, code, tree_path) VALUES (?, ?, CAST(? as ltree)) " +
                "WHEN EXIST (SELECT code FROM procurement_vocabulary WHERE code = ? AND title != ? OR tree_path != ?::ltree) THEN " +
                    "UPDATE procurement_vocabulary SET title=?, code=?, tree_path=?::ltree " +
                "ELSE DO NOTHING " +
                "END";
        jdbcTemplate.batchUpdate(sql, batch);
    }

    public PurchaseEntry getNode(String node) {
        String sql = "SELECT * FROM procurement_vocabulary " +
                "WHERE code ~ '" + node + "'";
        return jdbcTemplate.queryForObject(sql, mapper);
    }

    public List<PurchaseEntry> getSubNodes(String nodeTreePath) {
        String sql = "SELECT * FROM procurement_vocabulary " +
                "WHERE tree_path ~ '" + nodeTreePath + ".*{1}'";
        return jdbcTemplate.query(sql, mapper);
    }
}
