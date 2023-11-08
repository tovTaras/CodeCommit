package com.tov.domain;

import javax.persistence.*;

@Entity
@Table(name = "delivery", schema = "shipping_company")
public class DeliveryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Basic
    @Column(name = "apartmentnumber")
    private Integer apartmentnumber;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "delivery_time_aprox")
    private String deliveryTimeAprox;
    @Basic
    @Column(name = "delivery_time_real")
    private String deliveryTimeReal;
    @Basic
    @Column(name = "delivery_payment")
    private Float deliveryPayment;
    @ManyToOne
    @JoinColumn(name = "delivery_person_id", referencedColumnName = "id")
    private DeliveryPersonEntity deliveryPerson;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getApartmentnumber() {
        return apartmentnumber;
    }

    public void setApartmentnumber(Integer apartmentnumber) {
        this.apartmentnumber = apartmentnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryTimeAprox() {
        return deliveryTimeAprox;
    }

    public void setDeliveryTimeAprox(String deliveryTimeAprox) {
        this.deliveryTimeAprox = deliveryTimeAprox;
    }

    public String getDeliveryTimeReal() {
        return deliveryTimeReal;
    }

    public void setDeliveryTimeReal(String deliveryTimeReal) {
        this.deliveryTimeReal = deliveryTimeReal;
    }

    public Float getDeliveryPayment() {
        return deliveryPayment;
    }

    public void setDeliveryPayment(Float deliveryPayment) {
        this.deliveryPayment = deliveryPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryEntity that = (DeliveryEntity) o;

        if (id != that.id) return false;
        if (apartmentnumber != null ? !apartmentnumber.equals(that.apartmentnumber) : that.apartmentnumber != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (deliveryTimeAprox != null ? !deliveryTimeAprox.equals(that.deliveryTimeAprox) : that.deliveryTimeAprox != null)
            return false;
        if (deliveryTimeReal != null ? !deliveryTimeReal.equals(that.deliveryTimeReal) : that.deliveryTimeReal != null)
            return false;
        if (deliveryPayment != null ? !deliveryPayment.equals(that.deliveryPayment) : that.deliveryPayment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (apartmentnumber != null ? apartmentnumber.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (deliveryTimeAprox != null ? deliveryTimeAprox.hashCode() : 0);
        result = 31 * result + (deliveryTimeReal != null ? deliveryTimeReal.hashCode() : 0);
        result = 31 * result + (deliveryPayment != null ? deliveryPayment.hashCode() : 0);
        return result;
    }

    public DeliveryPersonEntity getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPersonEntity deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

}
