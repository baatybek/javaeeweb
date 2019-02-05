/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit.cvut.bietjv.project.carevidenceserver.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author beksultan
 */
@Entity
@XmlRootElement
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Line_ID;
    private Integer Capacity;
    
    @ManyToOne
    Driver driver;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine_ID() {
        return Line_ID;
    }

    public void setLine_ID(String Line_ID) {
        this.Line_ID = Line_ID;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer Capacity) {
        this.Capacity = Capacity;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fit.cvut.bietjv.project.carevidenceserver.entities.Bus[ id=" + id + " ]";
    }
    
}
