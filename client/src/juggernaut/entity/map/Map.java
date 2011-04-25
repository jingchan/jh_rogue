package juggernaut.entity.map;
import com.jme3.math.*;
public class Map {
	private Vector2f playerCoord;
	private TileGrid tileMap;
	
	
	public Vector2f getPlayerCoord(){
		return this.playerCoord;
	}
	
	public void setPlayerCoord(Vector2f coord){
		this.playerCoord = coord;
	}
	
	public TileGrid getTileGrid(){
		return this.tileMap;
	}
	public void setTileGrid(TileGrid tm){
		this.tileMap = tm;
	}
	
	
}
