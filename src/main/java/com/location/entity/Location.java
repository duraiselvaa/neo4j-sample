package com.location.entity;

import static org.hamcrest.Matchers.closeTo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
@Entity
@Table( name = "location" )
@XmlRootElement( name = "location" )
public class Location {
	
	@GraphId
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;
	
	@Column( nullable = false )
	private double latitude;
	@Column( nullable = false )
	private double longitude;
	
	
	public Location(){
		super();
	}
	public Location( final double latitudeToSet, final double longitudeToSet ){
		this.latitude = latitudeToSet;
		this.longitude = longitudeToSet;
	}


	public Long getId(){
		return id;
	}

	public void setId( final Long idToSet ){
		this.id = idToSet;
	}
	
	public double getLatitude(){
		return latitude;
	}
	public double getLongitude(){
		return longitude;
	}
	
	public void setLongitude( float longitudeToSet ){
		this.longitude = longitudeToSet;
	}
	public void setLatitude( float latitudeToSet ){
		this.latitude = latitudeToSet;
	}
	
	//
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits( latitude );
		result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
		temp = Double.doubleToLongBits( longitude );
		result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
		return result;
	}
	
	@Override
	public boolean equals( Object obj ){
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		
		final Location other = (Location) obj;
		
		if( !closeTo( latitude, 0.0001 ).matches( other.latitude ) ){
			return false;
		}
		if( !closeTo( longitude, 0.0001 ).matches( other.longitude ) ){
			return false;
		}
		
		return true;
	}
	@Override
	public String toString(){
		return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
