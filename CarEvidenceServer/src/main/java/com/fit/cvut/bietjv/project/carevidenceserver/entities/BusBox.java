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
public class BusBox {
    private List<Bus> buses = new ArrayList();

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
    
}
