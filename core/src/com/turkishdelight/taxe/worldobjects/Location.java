package com.turkishdelight.taxe.worldobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.turkishdelight.taxe.Game;
import com.turkishdelight.taxe.Scene;
import com.turkishdelight.taxe.SpriteComponent;

public class Location extends SpriteComponent {

	// Class that is designed to hold information on locations- location, connected waypoints
	static Texture text = new Texture("location.png");
	private Vector2 coords;			      						  // vector location of waypoint
	private Array<Location> connections = new Array<Location>();  // connected waypoints
	private int numConnections = 0;
	
	public Location(Scene parentScene, int x, int y) {
		super(parentScene, text, Game.locationZ);
		this.setSize(10, 10);
		coords = new Vector2(x, y);
	}
	
	public void addConnections(Array<Location> connections) {
		this.connections.addAll(connections);
		numConnections += connections.size;
	}
	
	public void addConnection(Location location) {
		this.connections.add(location);
		numConnections++;
	}
	
	public int numConnections() {
		return numConnections;
	}

	public boolean isConnected(Location location) {
		return this.connections.contains(location, false);
	}
	
	public Vector2 getCoords() {
		return this.coords;
	}

	public Array<Location> getConnections () {
		return this.connections;
	}
	
}

