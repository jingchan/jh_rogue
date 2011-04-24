package juggernaut;
import juggernaut.Tile;

public class TileMap {
	int xSize;
	int ySize;
	
	public Tile[][] grid;
	
	public TileMap () {
		grid = initGrid(10, 10);
	}
	public TileMap (int x, int y) {
		grid = initGrid(x, y);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || x >= xSize){
			return null;
		}
		
		if(y < 0 || y >= ySize){
			return null;
		}
		
		return grid[x][y];
	}
	
	private Tile[][] initGrid(int xNum, int yNum) {
		this.xSize = xNum;
		this.ySize = yNum;
		Tile ret[][];
		ret = new Tile[this.xSize][this.ySize];
		
		for (int i=0; i<this.xSize; i++){
			for (int j=0; j<this.ySize; j++){
				ret[i][j] = new Tile();
				ret[i][j].setType((i+j)%2+1);
			}
		}
				
		
		return ret;
	}
}
