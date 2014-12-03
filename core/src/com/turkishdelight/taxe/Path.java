package com.turkishdelight.taxe;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sun.istack.internal.NotNull;
import com.turkishdelight.taxe.worldobjects.Location;

public class Path implements Iterable<Location>{
	
	// Contains information on path from location to another 
	private Array<Location> locations;
	private int size = 0;
	
	public Path() {
		this.locations = new Array<Location>();
	}
	
	public Path(Location ... locations) { // note waypoints cannot be null
		this.locations = new Array<Location>();
		Location previous = null;
		for (Location location : locations){
			if (size == 0) {
				this.locations.add(location);
				
			} else {
				if (previous.isConnected(location)){
					this.locations.add(location);
				} else {
					System.out.println("NOT A VALID ROUTE");
				}
			}
			previous = location;	
			size++;
		}
	}
	
	public Array<Vector2> getLocations(){ // renaming from location?
		Array<Vector2> locations = new Array<Vector2>();
		for (Location location:this.locations){
			locations.add(location.getCoords());
		}
		return locations;
	}
	
	public void add(Location location){
		locations.add(location);
		size+=1;
	}
	public int size(){
		return size;
	}

	@Override
	public Iterator<Location> iterator() {
		// TODO Auto-generated method stub
		return locations.iterator();
	}
}
