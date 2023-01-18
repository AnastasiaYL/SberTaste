package com.example.sbertaste.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "common_generator", sequenceName = "order_id_seq", allocationSize = 1)
public class OrderEntity extends CommonEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "delivery_address")
    private String deliveryAddress;

//    @OneToMany
//    @JoinColumn(name = "order_position_id")
//    private List<OrderPositionEntity> orderPositions;

    @Column(name = "delivery_cost")
    private Double deliveryCost;

    @Column(name = "created_timestamp")
    private Date createdWhen;

    @Override
    public String toString() {
        return "OrderEntity{" +
                "customer=" + customer.getId() +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryCost=" + deliveryCost +
                ", createdWhen=" + createdWhen +
                '}';
    }
}
