package com.tov.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "car_size", schema = "shipping_company", catalog = "")
public class CarSizeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "diameter")
    private String diameter;
    @Basic
    @Column(name = "ingredient_cost_coef")
    private double ingredientCostCoef;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "price")
    private String price;
    @OneToMany(mappedBy = "carSizeByCarSizeId")
    private Collection<CarEntity> carsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public double getIngredientCostCoef() {
        return ingredientCostCoef;
    }

    public void setIngredientCostCoef(double ingredientCostCoef) {
        this.ingredientCostCoef = ingredientCostCoef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarSizeEntity that = (CarSizeEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.ingredientCostCoef, ingredientCostCoef) != 0) return false;
        if (diameter != null ? !diameter.equals(that.diameter) : that.diameter != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (diameter != null ? diameter.hashCode() : 0);
        temp = Double.doubleToLongBits(ingredientCostCoef);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    public Collection<CarEntity> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<CarEntity> carsById) {
        this.carsById = carsById;
    }
}
