package com.shusi.meal.model;

import com.shusi.ingredient.model.Ingredient;
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
import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    private String details;

    private String image;

    @Column(nullable = false)
    private Time properTime;

    @ManyToMany
    @Column(nullable = false)
    private Collection<Ingredient> ingredients;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private boolean possibleToDo;
}
