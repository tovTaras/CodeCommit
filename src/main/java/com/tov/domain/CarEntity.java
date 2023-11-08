package com.tov.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car", schema = "shipping_company", catalog = "")
public class CarEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "car_size_id", referencedColumnName = "id", nullable = false)
    private CarSizeEntity carSizeByCarSizeId;
    @ManyToMany(mappedBy = "cars")
    private Set<OrderEntity> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity that = (CarEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public CarSizeEntity getCarSizeByCarSizeId() {
        return carSizeByCarSizeId;
    }

    public void setCarSizeByCarSizeId(CarSizeEntity carSizeByCarSizeId) {
        this.carSizeByCarSizeId = carSizeByCarSizeId;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
