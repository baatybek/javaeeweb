/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit.cvut.bietjv.project.carevidenceserver.entities.service;

import com.fit.cvut.bietjv.project.carevidenceserver.entities.Bus;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.BusBox;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author beksultan
 */
@Stateless
@Path("com.fit.cvut.bietjv.project.carevidenceserver.entities.bus")
public class BusFacadeREST extends AbstractFacade<Bus> {

    @PersistenceContext(unitName = "com.fit.cvut.bietjv.project_CarEvidenceServer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public BusFacadeREST() {
        super(Bus.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bus entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Bus entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bus find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public BusBox findAllBuses() {
        BusBox buses = new BusBox();
        buses.setBuses(super.findAll());
        return buses;
    }
    
    @GET
    @Path("search/{searched}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public BusBox findSearchedBuses(@PathParam("searched") String searched) {
        List<Bus> allBusses = super.findAll();
        List<Bus> searchedBuses = new ArrayList();

        for (Bus bus : allBusses) {
            if( (bus.getLine_ID()!= null && bus.getLine_ID().toLowerCase().contains(searched.trim().toLowerCase())) ) {
                searchedBuses.add(bus);
            }
        }

        BusBox buses = new BusBox();
        buses.setBuses(searchedBuses); 
        return buses;

    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bus> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
