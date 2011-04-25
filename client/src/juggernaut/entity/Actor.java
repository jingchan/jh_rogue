package juggernaut.entity;
import com.jme3.math.*;

public abstract class Actor {
	protected Vector3f position;
	protected Vector3f direction;
	
	protected Actor(){
		System.out.print("Actor Created");
		this.position = Vector3f.ZERO;
		this.direction = Vector3f.UNIT_Y;
	}
	
	protected Actor( Vector3f pos ){
		System.out.print("Actor Created");
		this.position = pos;
		this.direction = Vector3f.UNIT_Y;
	}
	
	protected Actor( Vector3f pos, Vector3f dir ){
		System.out.print("Actor Created");
		this.position = pos;
		this.direction = dir;
	}
	
	public void setPosition( Vector3f pos ){
		this.position = pos;
		
		//restrict position to 0 z-axis
		this.position.z = 0.0f;
	}
	
	public Vector3f getPosition(){
		return this.position;
	}
	
	public void setDirection( Vector3f dir ){
		//Ensure that direction is normalized
		this.direction = dir.normalize();
		
		//restrict direction to x-y axis
		this.direction.z = 0.0f;
		this.direction = this.direction.normalize();
	}
	
	public Vector3f getDirection(){
		return this.direction;
	}
	
}
