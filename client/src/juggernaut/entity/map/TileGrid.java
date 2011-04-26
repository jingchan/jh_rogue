package juggernaut.entity.map;
import com.jme3.math.FastMath;

import juggernaut.entity.map.Tile;

public class TileGrid {
	int xSize;
	int ySize;
	
	public Tile[][] grid;
	
	public TileGrid () {
		grid = initGrid(10, 10);
	}
	public TileGrid (int x, int y) {
		grid = initGrid(x, y);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || x >= xSize){
			return null;
		}
		
		if(y < 0 || y >= ySize){
			return null;
		}
		
		return this.grid[x][y];
	}
	
	public void setTile(int type, int x, int y) {
		if(x < 0 || x >= xSize){
			return;
		}
		
		if(y < 0 || y >= ySize){
			return;
		}

		this.grid[x][y].setType(type);
	}
	
	public Tile tileAtWoordCoord(float x, float y){
		float tileSize = 2.0f;
		return getTile((int)FastMath.floor(x/tileSize), (int)FastMath.floor(y/tileSize));
	}
	
	private Tile[][] initGrid(int xNum, int yNum) {
		this.xSize = xNum;
		this.ySize = yNum;
		Tile ret[][];
		ret = new Tile[xSize][ySize];
		
		for (int i=0; i<xSize; i++){
			for (int j=0; j<ySize; j++){
				ret[i][j] = new Tile();
				//ret[i][j].setType((i+j)%2+1);
				//ret[i][j].setType(2);
			}
		}

		return ret;
	}
	
	public int getXSize(){
		return this.xSize;
	}
	
	public int getYSize(){
		return this.ySize;
	}
}
