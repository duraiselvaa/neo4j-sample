package com.location.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.location.entity.Location;

public interface LocationRepository  extends GraphRepository<Location> {
	
    Location findById( String id );
}
