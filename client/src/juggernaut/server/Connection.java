package juggernaut.server;

import juggernaut.entity.map.*;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.jme3.system.AppSettings;
import java.net.InetAddress;
import com.google.gson.*;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

class Connection {
	private boolean is_connection_open = false;
	private ServerInfo server_info;
	private String default_server = "http://localhost:8000/";
	private Gson parser;
		
	public Connection (final ServerInfo server_info) {
		this.is_connection_open = false;
		this.server_info = server_info;
		this.parser = new Gson();
	}
	
	public TileGrid get_random_tile_map() {
		String host = this.default_server + "core/generate_map/";
		String response = this.get_response(host);
		
		Type listType = new TypeToken<ArrayList<ArrayList<Integer>>>() {}.getType();
		
		ArrayList<ArrayList<Integer>> tuples = this.parser.fromJson(response, listType);
		TileGrid grid = new TileGrid(100, 100);
		for (ArrayList<Integer> tile_info : tuples) {
			grid.setTile(tile_info.get(2), tile_info.get(0), tile_info.get(1));
		}
		System.out.println(grid.toString());
		return grid;
	}
	
	public void test() {
		HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet("http://localhost:8000/admin/");

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");
        } catch (Exception e) {
        	System.out.println(e.toString());
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
	}
	
	public String get_response(String host) {
		HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(host);

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");
            return responseBody;
        } catch (Exception e) {
        	return "---";
        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
	}
	
	public static void main(String[] args) {
		String ip = "192.168.0.1";
		ServerInfo i = new ServerInfo(ip);
		Connection test_connection = new Connection(i);
		test_connection.get_random_tile_map();
	}
}