package com.turkishdelight.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AISprite extends SpriteComponent {
	
	// TODO need to be able to add route, maybe not construct with route?
	
	private Vector2 velocity = new Vector2(); // 2d vector
	private float speed = 50; // pixels per second to move
	private Array<Vector2> path; //array of vectors (velocities, points)
	private int waypoint = 0; // index of waypoint in above array
	private boolean completed = false; // boolean to say whether reached final waypoint
	private int midSpritex, midSpritey; // values to store midpoint of sprite, used in correcting calculations
	
	public AISprite(Scene parentScene, int z, Texture texture, Array<Vector2> path) {
		super(parentScene, texture, z);
		setPosition(path.get(0).x-25, path.get(0).y-25);
		this.path = path;
		waypoint++;
		midSpritex = 25;  //TODO possible constant-ification
		midSpritey = 25;
	}
	
	public void addPath(Array<Vector2> path){
		this.path = path;
	}
	
	// constantly updates to allow movement
	@Override
	public void draw(Batch batch) {
		if (!completed ) {
			update(Gdx.graphics.getDeltaTime()); // time between last frame, current frame
		} else {
			setPosition(getX(), getY()); // if completed route, stay at end of route.
		}
		super.draw(batch);
	}

	public void update(float delta) {
		// angle between current position and waypoint (in radians)
		float angle = (float) Math.atan2(path.get(waypoint).y - midSpritey - (getY()), path.get(waypoint).x - midSpritex - (getX())); // distance of current position to waypoint position
		velocity.set((float) Math.cos(angle) * speed,(float) Math.sin(angle) * speed); // always add up to 'speed' integer value
		
		setPosition(getX() + velocity.x * delta, getY()+ velocity.y * delta); // same speed across all framerates
		setRotation(angle * MathUtils.radiansToDegrees);
		
		if(isWaypointReached()) {
			// need code to save 'overshoot'
			setPosition(path.get(waypoint).x -midSpritex, path.get(waypoint).y - midSpritey); // stops it passing past waypoint in high speeds

			if(waypoint + 1 >= path.size) // if reached final waypoint, fix it to final waypoint
				completed = true;
			else
				waypoint++;	
		}
	}

	private boolean isWaypointReached() {
		// return true if sprite has reached waypoint
		// speed * time = distance travelled in one frame, compared to current distance
		// math .abs stops the occassional jumping
		return Math.abs(path.get(waypoint).x - midSpritex - getX()) <= speed * Gdx.graphics.getDeltaTime() && Math.abs(path.get(waypoint).y - midSpritey - getY()) <= speed * Gdx.graphics.getDeltaTime();
	}

	public Array<Vector2> getPath() {
		return path;
	}
	
	public int getWaypoint() {
		return waypoint;
	}
	
	public boolean isCompleted() {
		return completed;
	}
}
