package com.shusi.table;

import com.shusi.table.model.Table;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface TableService {

    Collection<Table> getAllTables();

    Optional<Table> getTable(Integer tableId);

    Table addTable(Table table) throws IllegalArgumentException;
}
