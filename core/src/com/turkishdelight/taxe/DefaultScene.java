package com.turkishdelight.taxe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.turkishdelight.taxe.worldobjects.Location;

public class DefaultScene extends Scene {
	Texture mapText;
	SpriteComponent map;

	private ShapeRenderer sr;
	private SpriteBatch batch; // changed from batch!
	private Array<AISprite> aiSprites; // things that will move around
	private SpriteComponent sprite;
	private Path masterPath;
	
	private Location London;
	private Location Rome;
	private Location Moscow;
	private Location Lisbon;
	private Location Paris;
	
	@Override
	public void onCreate()
	{
		sr = new ShapeRenderer();
		batch = new SpriteBatch();
		masterPath = new Path();
		
		mapText = new Texture("map.png");
		map = new SpriteComponent(this, mapText, Game.mapZ);
		map.setPosition(0, 0);
		map.setSize(Game.targetWindowsWidth, Game.targetWindowsHeight);
		
		
		//Locations setup
		London = createLocation(this, 210, 390);
		London.setPosition(210, 390);
		
		Rome = createLocation(this, 604, 168);
		Rome.setPosition(604, 168);
		
		Moscow = createLocation(this, 800, 500);
		Moscow.setPosition(800,500);
		
		Lisbon = createLocation(this, 100, 200);
		Lisbon.setPosition(100, 200);
		
		Paris = createLocation(this, 400, 400);
		Paris.setPosition(400,400); 
	
		Add(London);
		Add(Rome);
		Add(Lisbon);
		Add(Moscow);
		Add(Paris);
		Add(map);
		
		// setup base sprite
		Texture texture = new Texture("train1.png");

		connectLocations(Rome, Moscow);
		connectLocations(Moscow, Lisbon);
		connectLocations(London, Rome);
		connectLocations(London, Moscow);

		// populate with AISprites with paths
		aiSprites = new Array<AISprite>();
		for(int i = 0; i < 1; i++) {
			AISprite aiSprite = new AISprite(this, Game.objectsZ, texture, getSpritePath().getLocations());
			aiSprites.add(aiSprite);
			aiSprite.setSize(50, 50); // set size of train to 50x50
			aiSprite.setOriginCenter();
			Add(aiSprite);
		}
		
	}

	
	@Override
	public void Draw(SpriteBatch batch) {
		super.Draw(batch);
		
		this.batch.begin(); 
		for (AISprite aiSprite : aiSprites)
			aiSprite.draw(this.batch); // drawing at every frame all the time - even when finished
		this.batch.end();
		
		// TODO change to use sprites instead of ShapeRenderer
		sr.begin(ShapeType.Line);
		int i = 0;
		for (Location location: masterPath){
			Array<Location> connectedLocations = location.getConnections();
			for (Location connectedLocation : connectedLocations){
				sr.line(location.getCoords(), connectedLocation.getCoords());
				i++;
			}
		}
		sr.end();

		/*
		// draws points that current route is taking
		sr.begin(ShapeType.Filled);
		for (AISprite aiSprite :aiSprites) 
			for (Vector2 waypoint :aiSprite.getPath())
				sr.circle(waypoint.x, waypoint.y, 5); // every waypoint has circle (for ease of seeing them!)
		sr.end();*/

		// draw lines
		
		
	}

	// creates new location, adds it to masterPath
	private Location createLocation(Scene parentScene, int x, int y){
		Location location = new Location(parentScene, x,y);
		masterPath.add(location);
		return location;
	}

	// connects 2 waypoints both ways
	private void connectLocations(Location l1, Location l2){
		if (!(l1.isConnected(l2))) {
			l1.addConnection(l2);
			l2.addConnection(l1);
			System.out.println("connection added!");
		}
	}
	
	// return one of the paths
	private Path getSpritePath(){
		Path path;
		if (MathUtils.randomBoolean()) {
			path = new Path(Moscow, Lisbon);
		}
		else {
			path = new Path(Moscow, London, Rome);
		}
		return path;
	}
	
	@Override
	public void Update()
	{
		/*// draw AI sprite
		batch.begin(); 
		for (AISprite aiSprite : aiSprites)
			aiSprite.draw(batch); // drawing at every frame all the time - even when finished
		batch.end();*/
	}

}
