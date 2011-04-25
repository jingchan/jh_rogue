package juggernaut.entity;
import com.jme3.math.*;

public class MovableActor extends Actor {
	protected MovableActor(){
		super();
	}
	
	protected MovableActor( Vector3f pos ){
		super(pos);
	}
	
	protected MovableActor( Vector3f pos, Vector3f dir ){
		super(pos, dir);
	}
	
}
