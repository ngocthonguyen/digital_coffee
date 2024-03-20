package com.digitalcoffee.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dc_order")
public class OrderRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date orderDate;

    @Column
    private String customerUsername;

    @Column
    private String shopRef;

    @Column
    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderLineEntity> lines = new ArrayList<>(0);

    public void addLine(Long productId, int quantity){
        lines.add(new OrderLineEntity(null, productId, quantity, this));
    }
}
