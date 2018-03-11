package com.mimosa.order.model;

import com.mimosa.meal.model.Meal;
import com.mimosa.table.model.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "indent")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;
    private Integer summary;
    private Date dateStart;
    private Date dateEnd;

    @ManyToOne
    private Table table;

    @ManyToMany
    @Column(nullable = false)
    private Collection<Meal> meals;
}
