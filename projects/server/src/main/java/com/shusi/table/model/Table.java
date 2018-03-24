package com.shusi.table.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "Place")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Table implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
