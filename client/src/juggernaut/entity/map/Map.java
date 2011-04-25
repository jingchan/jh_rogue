package juggernaut.entity.map;

public class Map {
	private TileGrid tileMap;
	
	public TileGrid getTileGrid(){
		return this.tileMap;
	}
	public void setTileGrid(TileGrid tm){
		this.tileMap = tm;
	}
}
