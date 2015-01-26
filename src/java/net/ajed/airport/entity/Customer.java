/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ajed.airport.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrew
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByFirstname", query = "SELECT c FROM Customer c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Customer.findByLastname", query = "SELECT c FROM Customer c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Customer.findByPassportnumber", query = "SELECT c FROM Customer c WHERE c.passportnumber = :passportnumber")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "PASSPORTNUMBER")
    private Integer passportnumber;
    @JoinColumn(name = "LUGGAGEID", referencedColumnName = "ID")
    @ManyToOne
    private Luggage luggageid;
    @JoinColumn(name = "FLIGHTID", referencedColumnName = "ID")
    @ManyToOne
    private Flight flightid;
    @JoinColumn(name = "ADDRESSID", referencedColumnName = "ID")
    @ManyToOne
    private Address addressid;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(Integer passportnumber) {
        this.passportnumber = passportnumber;
    }

    public Luggage getLuggageid() {
        return luggageid;
    }

    public void setLuggageid(Luggage luggageid) {
        this.luggageid = luggageid;
    }

    public Flight getFlightid() {
        return flightid;
    }

    public void setFlightid(Flight flightid) {
        this.flightid = flightid;
    }

    public Address getAddressid() {
        return addressid;
    }

    public void setAddressid(Address addressid) {
        this.addressid = addressid;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ajed.airport.entity.Customer[ id=" + id + " name= " + this.firstname + " " + this.lastname + " Passport= " + this.passportnumber + " ]";
    }
    
}
