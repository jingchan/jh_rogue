package juggernaut.entity.map;
import com.jme3.math.*;
import juggernaut.entity.MovableActor;
import java.util.ArrayList;

public class Map {
	private Vector2f playerCoord;
	private TileGrid tileMap;
	private ArrayList<MovableActor> mobs;
	
	public Map() {
		this.mobs = new ArrayList<MovableActor>();
	}
	
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
	
	public void addMob(MovableActor mob) {
		this.mobs.add(mob);
	}
}
