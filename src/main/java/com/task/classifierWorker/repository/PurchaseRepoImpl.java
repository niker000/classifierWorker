package com.task.classifierWorker.repository;

import com.task.classifierWorker.mapper.PurchaseEntryMapper;
import com.task.classifierWorker.model.PurchaseEntry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            parameters = new Object[3];
            parameters[0] = purchaseEntry.getTitle();
            parameters[1] = purchaseEntry.getPurchaseCode();
            parameters[2] = purchaseEntry.getTreePath();

            batch.add(parameters);
        }
        String sql = "SELECT updatedb(?,?,?::ltree); ";

        jdbcTemplate.batchUpdate(sql, batch);

    }

    public PurchaseEntry getNode(String node) {
        String sql = "SELECT * FROM procurement_vocabulary " +
                "WHERE code = '" + node + "'";
        return jdbcTemplate.queryForObject(sql, mapper);
    }

    public List<PurchaseEntry> getSubNodes(String nodeTreePath) {
        String sql = "SELECT * FROM procurement_vocabulary " +
                "WHERE tree_path ~ '" + nodeTreePath + ".*{1}'";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<PurchaseEntry> getHighestEntries() {
        String sql = "SELECT * FROM procurement_vocabulary WHERE tree_path::varchar NOT LIKE '%.%'";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public boolean isExists(String code) {
        String sql = "SELECT * FROM procurement_vocabulary WHERE code='" + code + "'";
        return !jdbcTemplate.query(sql, mapper).isEmpty();
    }
}
