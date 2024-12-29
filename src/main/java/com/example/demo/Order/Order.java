package com.example.demo.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "total")
    private double total;

    public Order() {
    }

    public Order(UUID uuid, double total) {
        this.id = uuid;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(total, order.total) == 0 && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total);
    }
}
