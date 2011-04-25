package juggernaut.entity.map;

public class Tile {
	int type;
	
	public Tile() {
		this.type = 0;
	}
	public Tile(int type) {
		this.type = type;
	}
	public void setType(int type){
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	

}
