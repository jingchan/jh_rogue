package juggernaut.server;

import juggernaut.entity.map.*;
import juggernaut.entity.map.Map;
import juggernaut.entity.MovableActor;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.entity.ByteArrayEntity;

import com.jme3.system.AppSettings;
import java.net.InetAddress;
import com.google.gson.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.jme3.math.*;

public class Connection {
	private boolean isConnectionOpen = false;
	private ServerInfo serverInfo;
	private String default_server = "http://localhost:8000/";
	private Gson parser;
	private JsonParser rParser;
	
	public Connection (final ServerInfo serverInfo) {
		this.isConnectionOpen = false;
		this.serverInfo = serverInfo;
		this.parser = new Gson();
		this.rParser = new JsonParser();
	}
	
	public Connection() {
		this.isConnectionOpen = false;
		this.parser = new Gson();
		this.rParser = new JsonParser();
	}
	
	public TileGrid getRandomTileMap(int width, int height) {
		String host = this.default_server + "core/generate_tile_map/" + width + "/" + height + "/";
		String response = this.getResponse(host);
		
		Type listType = new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType();
		ArrayList<ArrayList<Integer>> tuples = this.parser.fromJson(response, listType);
		TileGrid grid = new TileGrid(100, 100);
		for (ArrayList<Integer> tile_info : tuples) {
			grid.setTile(tile_info.get(2), tile_info.get(0), tile_info.get(1));
		}

		return grid;
	}
	
	public Map getRandomMap(int width, int height, int mob_count) {
		String host = this.default_server + "core/generate_map/" + width + "/" + height;
		String response = this.getResponse(host);
		
		String tiles = response.split("-----")[0];
		String mobs = response.split("-----")[1];
		String startLocations = response.split("-----")[2];
		
		Map randomMap = new Map();
		
		TileGrid grid = this.parseTiles(this.rParser.parse(tiles).getAsJsonArray(), width, height);
		randomMap.setTileGrid(grid);
		
		JsonArray tree = this.rParser.parse(mobs).getAsJsonArray();
		for (JsonElement mobInfo : tree) {
			MovableActor newMob = this.parseMob(mobInfo.getAsJsonArray());
			randomMap.addMob(newMob);
		}
		
		for (JsonElement startingLocation : this.rParser.parse(startLocations).getAsJsonArray()) {
			randomMap.addStartingLocation(this.parseVector2f(startingLocation.getAsJsonArray()));
		}
		
		return randomMap;
	}
	
	public String getResponse(String host) {
		HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(host);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            return responseBody;
        } catch (Exception e) {
        	return null;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
	}
	
	public void test() {
		String host = this.default_server + "core/generate_map/" + 100 + "/" + 100;
		String response = this.getResponse(host);
		
		String tiles = response.split("-----")[0];
		String mobs = response.split("-----")[1];
		
		JsonParser parser = new JsonParser();
		JsonElement tree = parser.parse(mobs);
		JsonArray array = tree.getAsJsonArray();
		for (JsonElement x : array) {
			JsonArray currentElement = x.getAsJsonArray();
			for (JsonElement z : currentElement) {
				System.out.println(z.toString());
			}
		}
	}
	
	private TileGrid parseTiles(JsonArray tiles, int width, int height) {
		TileGrid grid = new TileGrid(width, height);
		
		for (JsonElement element : tiles) {
			JsonArray tileInfo = element.getAsJsonArray();
			grid.setTile(tileInfo.get(2).getAsInt(), tileInfo.get(0).getAsInt(), tileInfo.get(1).getAsInt());
		}
		
		return grid;
	}
	
	private MovableActor parseMob(JsonArray mobInfo) {
		JsonElement mobId = mobInfo.get(0);
		JsonArray mobPosition = mobInfo.get(1).getAsJsonArray();
		JsonArray mobDirection = mobInfo.get(2).getAsJsonArray();
		String mobAsset = mobInfo.get(3).getAsString();
		
		Vector3f mobPositionVector = this.parseVector3f(mobPosition);
		Vector3f mobDirectionVector = this.parseVector3f(mobDirection);
		
		MovableActor mob = new MovableActor(mobPositionVector, mobDirectionVector);
		return mob;
	}
	
	private Vector3f parseVector3f(JsonArray jVector) {
		Vector3f parsedVector = new Vector3f(jVector.get(0).getAsFloat(), jVector.get(1).getAsFloat(), jVector.get(2).getAsFloat());
		return parsedVector;
	}
	
	private Vector2f parseVector2f(JsonArray jVector) {
		Vector2f parsedVector = new Vector2f(jVector.get(0).getAsFloat(), jVector.get(1).getAsFloat());
		return parsedVector;
	}
	
	public static void main(String[] args) {
		String ip = "192.168.0.1";
		ServerInfo i = new ServerInfo(ip);
		Connection test_connection = new Connection(i);
		Map new_map = test_connection.getRandomMap(100, 100, 20);
		//test_connection.test();
	}
}