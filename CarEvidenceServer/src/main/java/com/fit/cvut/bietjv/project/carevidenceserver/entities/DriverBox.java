/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit.cvut.bietjv.project.carevidenceserver.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beksultan
 */
public class DriverBox {
    private List<Driver> drivers = new ArrayList();

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
    
}
