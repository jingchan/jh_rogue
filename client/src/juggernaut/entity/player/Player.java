package juggernaut.entity.player;
import com.jme3.math.*;

import juggernaut.entity.player.PlayerState;
import juggernaut.entity.*;

public class Player extends MovableActor {
	PlayerState state;
	
	public Player() {
		super();
		state = PlayerState.IDLE;
	}
	
	public Player(Vector3f pos) {
		super(pos);
		state = PlayerState.IDLE;
	}
	
	public Player(Vector3f pos, Vector3f dir) {
		super(pos, dir);
		state = PlayerState.IDLE;
	}
	
//	public void update( float dt ){
//		float speed = 10.0f;
//		switch (state) {
//		case IDLE:
//			break;
//		case WALKING:
//			this.position = position.add(direction.normalize().mult(0.5f*speed*dt));
//			break;
//		case RUNNING:
//			this.position = position.add(direction.normalize().mult(speed*dt));
//			break;
//		}
//	}
	
	public void setState( PlayerState st ){
		state = st;
	}
	
	
//	void setPosition( Vector3f pos ) {
//		this.position = pos.clone();
//		
//		//ensure that player position stays grounded
//		if(position.z > 0.0f) {
//			position.z = 0.0f;
//			System.out.printf("Error: Player z-position not 0.0f");
//		}
//	}
	
}
