package com.digitalcoffee.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mc_order_line")
public class OrderLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long productId;

    @Column
    private int nb;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private OrderRoot order;

}
