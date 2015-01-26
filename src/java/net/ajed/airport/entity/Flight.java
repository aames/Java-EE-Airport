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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FLIGHT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f"),
    @NamedQuery(name = "Flight.findById", query = "SELECT f FROM Flight f WHERE f.id = :id"),
    @NamedQuery(name = "Flight.findByDestination", query = "SELECT f FROM Flight f WHERE f.destination = :destination")})
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESTINATION")
    private String destination;
    @OneToMany(mappedBy = "flightid")
    private Collection<Customer> customerCollection;
    @JoinColumn(name = "GATEID", referencedColumnName = "ID")
    @ManyToOne
    private Gate gateid;

    public Flight() {
    }

    public Flight(Integer id) {
        this.id = id;
    }

    public Flight(Integer id, String destination) {
        this.id = id;
        this.destination = destination;
    }
    public Flight(Integer id, String destination, Gate gate) {
        this.id = id;
        this.destination = destination;
        this.gateid = gate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Gate getGateid() {
        return gateid;
    }

    public void setGateid(Gate gateid) {
        this.gateid = gateid;
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
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ajed.airport.entity.Flight[ id=" + id + " ]";
    }
    
}
