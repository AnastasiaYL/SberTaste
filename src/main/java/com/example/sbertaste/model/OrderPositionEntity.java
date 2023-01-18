package com.example.sbertaste.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "common_generator", sequenceName = "order_position_id_seq", allocationSize = 1)
public class OrderPositionEntity extends CommonEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id")
    private PizzaEntity pizza;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @Override
    public String toString() {
        return "OrderPositionEntity{" +
                "order=" + order.getId() +
                ", pizza=" + pizza.getId() +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
