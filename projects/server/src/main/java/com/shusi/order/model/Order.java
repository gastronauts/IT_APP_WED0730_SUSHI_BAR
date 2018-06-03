package com.shusi.order.model;

import com.shusi.table.model.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@javax.persistence.Table(name = "indent")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(unique = true)
    private String id;

    @Column(nullable = false)
    private Status status;

    private Integer summaryPrice;

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    @ManyToOne
    private Table table;

    @OneToMany
    private Collection<OrderedMeal> meals;
}
