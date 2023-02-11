package com.example.sbertaste.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "common_generator", sequenceName = "order_id_seq", allocationSize = 1)
public class OrderEntity extends CommonEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<OrderPositionEntity> orderPositions;

    @Column(name = "delivery_cost")
    private int deliveryCost;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "phone")
    private String phone;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_timestamp")
    private LocalDateTime createdWhen;

}
