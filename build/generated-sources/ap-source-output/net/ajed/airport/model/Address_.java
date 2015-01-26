package net.ajed.airport.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.ajed.airport.model.Customer;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-01-05T19:35:37")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, String> nameornumber;
    public static volatile SingularAttribute<Address, String> postalcode;
    public static volatile CollectionAttribute<Address, Customer> customerCollection;
    public static volatile SingularAttribute<Address, String> towncity;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, String> statecounty;
    public static volatile SingularAttribute<Address, String> country;

}