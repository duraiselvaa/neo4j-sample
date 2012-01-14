package com.location.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import com.location.repository.LocationRepository;

@Service
public class LocationService {
	
	static final Logger logger = LoggerFactory.getLogger( DataPopulatorService.class );

	@Autowired
    Neo4jTemplate template;

    @Autowired
    LocationRepository locationRepository;    
 	

}
