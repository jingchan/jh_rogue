package juggernaut.entity.map;
import com.jme3.math.*;
import java.io.*;
import java.util.Scanner;
public class SimpleMapParser extends MapParser {
	private String mapFile;
	
	public SimpleMapParser(String mapFile){
		this.mapFile = mapFile;
	}
	
	@Override
	public Map getMap() {
		TileGrid tg = null;
		
		FileInputStream fStream = null;
		try{
			fStream = new FileInputStream(mapFile);
		} catch (Exception e) {
			System.err.printf("File not found: %s\n", mapFile);
		}
		//InputStreamReader in= new DataInputStream(fStream);
		Scanner scan = new Scanner(fStream, "UTF-8");
	
		try{
			int rows = scan.nextInt();
			int cols = scan.nextInt();
			tg = new TileGrid(rows, cols);
			
			System.out.printf("Rows: %d Cols:%d\n", rows, cols);
			for(int i=0; i<rows; i++){
				String s = scan.next();
				for(int j=0; j<cols; j++){
					try{
					char c = s.charAt(j);
					//char ch = inStream.readChar();
						tg.setTile(Character.getNumericValue(c), i, j);
					}
					catch (Exception e) {

					}
				}
			}
			
			
		}
		catch (Exception e){
			System.err.print(e.toString());
		}
		finally {
			scan.close();
		}
		
		Map retMap = null;
		retMap = new Map();
		retMap.setTileGrid(tg);
		retMap.setPlayerCoord(new Vector2f(tg.getXSize()/2.0f, tg.getYSize()/2.0f));
		
		return retMap;
	}

	@Override
	public TileGrid getTileGrid() {
		// TODO Auto-generated method stub
		return null;
	}

}
