package com.task.classifierWorker.mapper;

import com.task.classifierWorker.model.PurchaseEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class PurchaseEntryMapper implements RowMapper<PurchaseEntry> {
    @Override
    public PurchaseEntry mapRow(ResultSet resultSet, int i) throws SQLException {
        PurchaseEntry purchaseEntry = new PurchaseEntry();
        purchaseEntry.setId(resultSet.getInt("id"));
        purchaseEntry.setTitle(resultSet.getString("title"));
        purchaseEntry.setPurchaseCode(resultSet.getString("code"));
        purchaseEntry.setTreePath(resultSet.getString("tree_path"));

        return purchaseEntry;
    }
}
