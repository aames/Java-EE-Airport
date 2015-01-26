package net.ajed.airport.dto;

import java.io.Serializable;

/**
 * as per http://www.oracle.com/technetwork/java/transferobject-139757.html
 * @author Andrew
 */
public class AddressDTO implements Serializable {
    private Integer id;
    private String nameornumber;
    private String street;
    private String towncity;
    private String statecounty;
    private String country;
    private String postalcode;
    
    
    public AddressDTO(Integer id, String nameornumber, String street, String towncity, String statecounty, String country, String postalcode) {
        this.id = id;
        this.nameornumber = nameornumber;
        this.street = street;
        this.towncity = towncity;
        this.statecounty = statecounty;
        this.country = country;
        this.postalcode = postalcode;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name or number
     */
    public String getNameornumber() {
        return nameornumber;
    }

    /**
     * @param nameornumber the name or number to set
     */
    public void setNameornumber(String nameornumber) {
        this.nameornumber = nameornumber;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the town city
     */
    public String getTowncity() {
        return towncity;
    }

    /**
     * @param towncity the town city to set
     */
    public void setTowncity(String towncity) {
        this.towncity = towncity;
    }

    /**
     * @return the state county
     */
    public String getStatecounty() {
        return statecounty;
    }

    /**
     * @param statecounty the state county to set
     */
    public void setStatecounty(String statecounty) {
        this.statecounty = statecounty;
    }

    /**
     * @return the postal code
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * @param postalcode the postal code to set
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
