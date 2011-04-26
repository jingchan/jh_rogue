package juggernaut.entity;

import com.jme3.math.*;
import juggernaut.entity.map.*;

public class MovableActor extends Actor {
	Map currentMap;
	MovableActorAction currentAction;
	Actor actionTarget;
	
	public MovableActor(){
		super();
		currentMap = null;
		currentAction = MovableActorAction.STANDING;
	}
	
	public MovableActor( Vector3f pos ){
		super(pos);
		currentMap = null;
		currentAction = MovableActorAction.STANDING;
	}
	
	public MovableActor( Vector3f pos, Vector3f dir ){
		super(pos, dir);
		currentMap = null;
		currentAction = MovableActorAction.STANDING;
	}
	
	void attachMap( Map map ){
		this.currentMap = map;
	}
	
	public void stand(){
		currentAction = MovableActorAction.STANDING;
		actionTarget = null;
	}
	public void walkTo(float x, float y){
		currentAction = MovableActorAction.WALKING;
		actionTarget = new WayPoint(new Vector3f(x, y, 0));
	}
	public void runTo(float x, float y){
		currentAction = MovableActorAction.RUNNING;
		actionTarget = new WayPoint(new Vector3f(x, y, 0));
	}
	
	
	public void update( float dt ){
		float speed = 10.0f;
		Vector3f newPosition;
		switch (currentAction) {
		case STANDING:
			break;
		case WALKING:
			setDirection(this.actionTarget.getPosition().subtract(this.position).normalize());
			newPosition = this.position.add(this.direction.mult(0.5f*speed*dt));
			if(newPosition.distance(this.position) > actionTarget.getPosition().distance(this.position)){
				newPosition = actionTarget.getPosition();
				stand();
			}
			setPosition(newPosition);
			break;
		case RUNNING:
			setDirection(this.actionTarget.getPosition().subtract(this.position).normalize());
			newPosition = this.position.add(this.direction.mult(speed*dt));
			if(newPosition.distance(this.position) > actionTarget.getPosition().distance(this.position)){
				newPosition = actionTarget.getPosition();
				stand();
			}
			setPosition(newPosition);
			break;
		}
	}
	
	private void moveTowards(Vector3f destination, float speed, float dt){
		if(currentMap == null){
			System.out.print("Map not attached, walking without collisions");
			setDirection(this.actionTarget.getPosition().subtract(this.position).normalize());
			Vector3f newPosition = this.position.add(this.direction.mult(speed*dt));
			if(newPosition.distance(this.position) > actionTarget.getPosition().distance(this.position)){
				// Arrived at destination, then stop walking
				newPosition = actionTarget.getPosition();
				stand();
			}
			setPosition(newPosition);
		} else {
			
		}
		
	}
}
