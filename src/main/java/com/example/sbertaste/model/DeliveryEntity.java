package com.example.sbertaste.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "common_generator", sequenceName = "delivery_id_seq", allocationSize = 1)
public class DeliveryEntity extends CommonEntity {

    @Column(name = "type")
    private String type;

    @Column(name = "cost")
    private int cost;

    @Column(name = "minimal_cart_for_free_delivery")
    private int minimalCartForFreeDelivery;
}
