/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit.cvut.bietjv.project.carevidenceserver.entities.service;

import com.fit.cvut.bietjv.project.carevidenceserver.entities.Driver;
import com.fit.cvut.bietjv.project.carevidenceserver.entities.DriverBox;
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
@Path("com.fit.cvut.bietjv.project.carevidenceserver.entities.driver")
public class DriverFacadeREST extends AbstractFacade<Driver> {

    @PersistenceContext(unitName = "com.fit.cvut.bietjv.project_CarEvidenceServer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public DriverFacadeREST() {
        super(Driver.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Driver entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Driver entity) {
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
    public Driver find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DriverBox findAllDrivers() {
        DriverBox drivers = new DriverBox();
        drivers.setDrivers(super.findAll());
        return drivers;
    }
    
    @GET
    @Path("search/{searched}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DriverBox findSearchedDrivers(@PathParam("searched") String searched) {
        List<Driver> allDrivers = super.findAll();
        List<Driver> searchedDrivers = new ArrayList();

        for (Driver driver : allDrivers) {
            if (  (driver.getName() != null && driver.getName().toLowerCase().contains(searched.trim().toLowerCase()))
                    || (driver.getSurname()!= null && driver.getSurname().toLowerCase().contains(searched.trim().toLowerCase())) ) {
                searchedDrivers.add(driver);
            }
        }

        DriverBox drivers = new DriverBox();
        drivers.setDrivers(searchedDrivers);
        return drivers;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Driver> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
