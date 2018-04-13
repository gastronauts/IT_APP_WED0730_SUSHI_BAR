package com.shusi.table;

import com.shusi.table.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/table")
public class TableController {

    @Autowired
    public TableService tableService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Table>> getTable(){
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Table> addTable(@RequestBody Table table){
        try {
            return new ResponseEntity<>(tableService.addTable(table),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
