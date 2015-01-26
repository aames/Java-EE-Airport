/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ajed.airport.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "LUGGAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Luggage.findAll", query = "SELECT l FROM Luggage l"),
    @NamedQuery(name = "Luggage.findById", query = "SELECT l FROM Luggage l WHERE l.id = :id"),
    @NamedQuery(name = "Luggage.findByMass", query = "SELECT l FROM Luggage l WHERE l.mass = :mass")})
public class Luggage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MASS")
    private Double mass;
    @OneToMany(mappedBy = "luggageid")
    private Collection<Customer> customerCollection;

    public Luggage() {
    }

    public Luggage(Integer id) {
        this.id = id;
    }

    public Luggage(Integer id, Double mass) {
        this.id = id;
        this.mass = mass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Luggage)) {
            return false;
        }
        Luggage other = (Luggage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ajed.airport.entity.Luggage[ id=" + id + " ]";
    }

}
