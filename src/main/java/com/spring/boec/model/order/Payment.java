package com.spring.boec.model.order;

import com.spring.boec.model.user.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
@Builder
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_money")
    private long totalMoney;

    @Column(name = "is_paid")
    private boolean isPaid;

    @JoinColumn(name = "order_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "customer_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Customer customer;
}
