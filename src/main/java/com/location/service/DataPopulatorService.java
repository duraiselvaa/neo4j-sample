package com.location.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.location.entity.Location;
import com.location.repository.LocationRepository;

@Service
public class DataPopulatorService {
	static final Logger logger = LoggerFactory.getLogger( DataPopulatorService.class );

	@Autowired
    Neo4jTemplate template;

    @Autowired
    LocationRepository locationRepository;    
 
    @Transactional
    public List<Location> populateDatabase() {
        List<Location> result=new ArrayList<Location>();
        
        result.add(locationRepository.save( new Location( 40.714, -74.006 )));
        result.add(locationRepository.save( new Location( 40.759011, -73.9844722)));
        result.add(locationRepository.save( new Location( 40.718, -74.008)));
        result.add(locationRepository.save( new Location( 40.705, -74.009)));
        result.add(locationRepository.save( new Location( 40.725, -74)));
        result.add(locationRepository.save( new Location( 40.7310, -73.996)));
        result.add(locationRepository.save( new Location( 40.65, -73.95)));
        result.add(locationRepository.save( new Location( 20.65, -13.95)));
		
        return result;
    }

    @Transactional
    public void cleanDb() {
        //new Neo4jDatabaseCleaner((AbstractGraphDatabase) template.getGraphDatabaseService()).cleanDb();
    }	

}
