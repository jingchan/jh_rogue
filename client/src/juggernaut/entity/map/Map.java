package juggernaut.entity.map;
import com.jme3.math.*;
import juggernaut.entity.MovableActor;
import java.util.ArrayList;

public class Map {
	private Vector2f playerCoord;
	private TileGrid tileMap;
	private ArrayList<MovableActor> mobs;
	private ArrayList<Vector2f> startingLocations;
	
	public Map() {
		this.mobs = new ArrayList<MovableActor>();
		this.startingLocations = new ArrayList<Vector2f>();
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
	
	public void addStartingLocation(Vector2f location) {
		this.startingLocations.add(location);
	}
	
	public Vector2f getStartingLocation() {
		return this.startingLocations.get(0);
	}
}
