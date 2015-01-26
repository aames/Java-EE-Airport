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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByNameornumber", query = "SELECT a FROM Address a WHERE a.nameornumber = :nameornumber"),
    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
    @NamedQuery(name = "Address.findByTowncity", query = "SELECT a FROM Address a WHERE a.towncity = :towncity"),
    @NamedQuery(name = "Address.findByStatecounty", query = "SELECT a FROM Address a WHERE a.statecounty = :statecounty"),
    @NamedQuery(name = "Address.findByCountry", query = "SELECT a FROM Address a WHERE a.country = :country"),
    @NamedQuery(name = "Address.findByPostalcode", query = "SELECT a FROM Address a WHERE a.postalcode = :postalcode")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAMEORNUMBER")
    private String nameornumber;
    @Size(max = 75)
    @Column(name = "STREET")
    private String street;
    @Size(max = 50)
    @Column(name = "TOWNCITY")
    private String towncity;
    @Size(max = 50)
    @Column(name = "STATECOUNTY")
    private String statecounty;
    @Size(max = 50)
    @Column(name = "COUNTRY")
    private String country;
    @Size(max = 50)
    @Column(name = "POSTALCODE")
    private String postalcode;
    @OneToMany(mappedBy = "addressid")
    private Collection<Customer> customerCollection;

    public Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameornumber() {
        return nameornumber;
    }

    public void setNameornumber(String nameornumber) {
        this.nameornumber = nameornumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTowncity() {
        return towncity;
    }

    public void setTowncity(String towncity) {
        this.towncity = towncity;
    }

    public String getStatecounty() {
        return statecounty;
    }

    public void setStatecounty(String statecounty) {
        this.statecounty = statecounty;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ajed.airport.entity.Address[ id=" + id + " ]";
    }
    
}
