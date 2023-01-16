package com.example.sbertaste.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "common_generator", sequenceName = "pizza_id_seq", allocationSize = 1)
public class PizzaEntity extends CommonEntity {


    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

}