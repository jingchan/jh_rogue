package juggernaut;
import com.jme3.math.*;
import juggernaut.PlayerState;

public class Player {
	Vector3f position;
	Vector3f direction;
	PlayerState state;
	
	Player() {
		position = new Vector3f(0.0f, 0.0f, 0.0f);
		direction = new Vector3f(0.0f, 1.0f, 0.0f);
		state = PlayerState.IDLE;
	}
	
	Player(Vector3f pos) {
		position = pos.clone();
		direction = new Vector3f(0.0f, 1.0f, 0.0f);
		state = PlayerState.IDLE;
	}
	
	Player(Vector3f pos, Vector3f dir) {
		position = pos.clone();
		direction = dir.clone();
		state = PlayerState.IDLE;
	}
	
	void update( float dt ){
		float speed = 10.0f;
		switch (state) {
		case IDLE:
			break;
		case WALKING:
			position = position.add(direction.normalize().mult(0.5f*speed*dt));
			break;
		case RUNNING:
			position = position.add(direction.normalize().mult(speed*dt));
			break;
		}
	}
	
	void setState( PlayerState st ){
		state = st;
	}
}
