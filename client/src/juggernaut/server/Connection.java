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
	private boolean is_connection_open = false;
	private ServerInfo server_info;
	private String default_server = "http://localhost:8000/";
	private Gson parser;
	private JsonParser rParser;
	
	public Connection (final ServerInfo server_info) {
		this.is_connection_open = false;
		this.server_info = server_info;
		this.parser = new Gson();
		this.rParser = new JsonParser();
	}
	
	public Connection() {
		
	}
	
	public TileGrid get_random_tile_map(int width, int height) {
		String host = this.default_server + "core/generate_tile_map/" + width + "/" + height + "/";
		String response = this.get_response(host);
		
		Type listType = new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType();
		ArrayList<ArrayList<Integer>> tuples = this.parser.fromJson(response, listType);
		TileGrid grid = new TileGrid(100, 100);
		for (ArrayList<Integer> tile_info : tuples) {
			grid.setTile(tile_info.get(2), tile_info.get(0), tile_info.get(1));
		}

		return grid;
	}
	
	public Map get_random_map(int width, int height, int mob_count) {
		String host = this.default_server + "core/generate_map/" + width + "/" + height;
		String response = this.get_response(host);
		
		String tiles = response.split("-----")[0];
		String mobs = response.split("-----")[1];
		
		Map randomMap = new Map();
		
		Type tileType = new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType();
		ArrayList<ArrayList<Integer>> tileList = this.parser.fromJson(tiles, tileType);
		
		TileGrid grid = new TileGrid(width, height);
		
		for (ArrayList<Integer> tile_info : tileList) {
			grid.setTile(tile_info.get(2), tile_info.get(0), tile_info.get(1));
		}
		
		randomMap.setTileGrid(grid);
		
		JsonElement tree = this.rParser.parse(mobs);
		JsonArray array = tree.getAsJsonArray();
		for (JsonElement mobInfo : tree.getAsJsonArray()) {
			MovableActor newMob = this.parseMob(mobInfo.getAsJsonArray());
			System.out.println(newMob.toString());
			randomMap.addMob(newMob);
		}
		
		return randomMap;
	}
	
	public String get_response(String host) {
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
		String response = this.get_response(host);
		
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
	
	private MovableActor parseMob(JsonArray mobInfo) {
		JsonElement mobId = mobInfo.get(0);
		JsonArray mobPosition = mobInfo.get(1).getAsJsonArray();
		JsonArray mobDirection = mobInfo.get(2).getAsJsonArray();
		String mobAsset = mobInfo.get(3).getAsString();
		
		Vector3f mobPositionVector = new Vector3f(mobPosition.get(0).getAsFloat(), mobPosition.get(1).getAsFloat(), mobPosition.get(2).getAsFloat());
		Vector3f mobDirectionVector = new Vector3f(mobDirection.get(0).getAsFloat(), mobDirection.get(1).getAsFloat(), mobDirection.get(2).getAsFloat());
		
		MovableActor mob = new MovableActor(mobPositionVector, mobDirectionVector);
		return mob;
	}
	
	public static void main(String[] args) {
		String ip = "192.168.0.1";
		ServerInfo i = new ServerInfo(ip);
		Connection test_connection = new Connection(i);
		Map new_map = test_connection.get_random_map(100, 100, 20);
		//test_connection.test();
	}
}