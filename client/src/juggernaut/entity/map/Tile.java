package juggernaut.entity.map;

public class Tile {
	private int type;
	private boolean walkable;
	
	public Tile() {
		this.type = 0;
		this.setWalkable(false);
	}
	public Tile(int type) {
		setType(type);
		
	}
	public void setType(int type){
		if(type == 1) 
			this.setWalkable(true);
		else 
			this.setWalkable(false);
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	

}
