package com.location.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.location.entity.Location;
import com.location.repository.LocationRepository;
import com.location.service.DataPopulatorService;
import org.neo4j.helpers.collection.IteratorUtil;


@Controller

public class LocationController {
	
    private LocationRepository locationRepository;
    private final Neo4jTemplate template;
    private DataPopulatorService populator;

    @Autowired
    public LocationController(LocationRepository locationRepository, DataPopulatorService populator, Neo4jTemplate template) {
        this.locationRepository = locationRepository;
        this.populator = populator;
        this.template = template;
    }
    
    @RequestMapping(value = "/locations/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Location getLocation(@PathVariable Long id) {
        return locationRepository.findOne(id);
    }
    
    @RequestMapping(value = "/locations", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Collection<Location> getLocations() {
    	return IteratorUtil.asCollection(locationRepository.findAll());
    }
    
    
    @RequestMapping(value = "/locations/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @ResponseBody
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteLocation(@PathVariable Long id) {
    	Location location = locationRepository.findOne(id);
    	locationRepository.delete(location);
    }
    
    @RequestMapping(value = "/locations",  method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    @ResponseStatus( value = HttpStatus.CREATED )
    public final void create( @RequestBody final Location entity, final HttpServletResponse response ){
    	locationRepository.save(entity);
    	response.setHeader( "Location", "/locations/" + entity.getId() );
    }    
    
    /**
     * overwriting the old values. 
     * @param entity
     * @param response
     */
    @RequestMapping(value = "/locations",  method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseBody
    public final void update( @RequestBody final Location entity, final HttpServletResponse response ){
    	locationRepository.save(entity);
    	response.setHeader( "Location", "/locations/" + entity.getId() );
    }    
    
    
    @RequestMapping(value = "/populate", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Collection<Location> populateDatabase(Model model) {
        Collection<Location> locations=populator.populateDatabase();
        return IteratorUtil.asCollection(locationRepository.findAll());
        
        /*model.addAttribute("locations",locations);
        return "/locations";*/
    }    
}
