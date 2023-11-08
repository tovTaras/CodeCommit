package com.tov.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "office", schema = "shipping_company")
public class OfficeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Basic
    @Column(name = "street_address")
    private String streetAddress;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "office_head")
    private String officeHead;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntity city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOfficeHead() {
        return officeHead;
    }

    public void setOfficeHead(String officeHead) {
        this.officeHead = officeHead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfficeEntity that = (OfficeEntity) o;

        if (id != that.id) return false;
        if (streetAddress != null ? !streetAddress.equals(that.streetAddress) : that.streetAddress != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (officeHead != null ? !officeHead.equals(that.officeHead) : that.officeHead != null) return false;

        return true;
    }


    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    @Override
    public int hashCode() { return Objects.hash(id, streetAddress, phone, officeHead, city );}
}
