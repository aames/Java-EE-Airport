package net.ajed.airport.dto;

import java.io.Serializable;

/**
 *
 * @author Andrew
 */
public class GateDTO implements Serializable {
    private Integer id;
    private String name;

    public GateDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
