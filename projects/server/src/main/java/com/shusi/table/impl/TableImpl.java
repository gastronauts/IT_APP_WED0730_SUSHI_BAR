package com.shusi.table.impl;

import com.shusi.table.TableService;
import com.shusi.table.model.Table;
import com.shusi.table.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class TableImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Collection<Table> getAllTables() {
        return (Collection<Table>) tableRepository.findAll();
    }

    @Override
    public Optional<Table> getTable(Integer tableId) {
        return Optional.ofNullable(tableRepository.findOne(tableId));
    }

    @Override
    public Table addTable(Table table) throws IllegalArgumentException {
        if(!tableRepository.exists(table.getId())){
            try {
                return tableRepository.save(table);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Table does already exist");
    }
}
