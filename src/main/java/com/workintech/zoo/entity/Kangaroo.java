package com.workintech.zoo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Kangaroo {
    private int id;
    private String name;
    private double height;
    private double weight;
    private String gender;
    private Boolean isAggressive;
}