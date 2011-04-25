package juggernaut;
 
import com.jme3.app.SimpleApplication;

//import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
//import com.jme3.input.controls.KeyTrigger;
//import com.jme3.light.AmbientLight;
//import com.jme3.light.DirectionalLight;

import com.jme3.math.*;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;
import com.jme3.system.AppSettings;
import com.jme3.material.Material;

import com.jme3.collision.*;

import juggernaut.TileMap;
import juggernaut.Player;
import juggernaut.PlayerState;

public class MainGame extends SimpleApplication implements ActionListener {
	private TileMap tileMap;
	private Node mapRootNode;
	private Player player;
	private Geometry playerModel;
	private Geometry mouseIndicator;
	private boolean mouseActive;
	public static void main(String[] args) {
		MainGame app = new MainGame();
		AppSettings settings = new AppSettings(true);
		
		settings.setTitle("Juggernaut - Pre Alpha");
		settings.setBitsPerPixel(24);
		settings.setResolution(800, 600);
		settings.setSamples(2);
		app.setSettings(settings);
		app.setShowSettings(false);
		app.start();
	}
	 
	public void simpleInitApp() {
		Box mouseBox = new Box(Vector3f.ZERO, 0.1f, 0.1f, 0.1f);
		Material mouseMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mouseMaterial.setColor("Color", ColorRGBA.Blue);
		mouseIndicator = new Geometry("Mouse Indicator", mouseBox);
		mouseIndicator.setMaterial(mouseMaterial);
		rootNode.attachChild(mouseIndicator);
		initMap();
		initPlayer();
		initKeys();
		initCamera();
	}

	@Override
	public void onAction(String arg0, boolean arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void simpleUpdate( float tpf ) {
		player.update(tpf);
		updateCamera();
		updateDrawEntities();
		handleInputs();
	}
	
	private void initCamera(){
		viewPort.setBackgroundColor(new ColorRGBA(172.0f/255.0f,214.0f/255.0f,243.0f/255.0f,1f));

		// Disable flyCam (instantiated by SimpleApplication)
		flyCam.setEnabled(false);
		
		// Set up camera with default values
		cam.getLocation().set(0.0f, 0.0f, 0.0f);
		cam.getUp().set(0.0f,0.0f,1.0f);
		cam.getLeft().set(-1.0f,0.0f,0.0f);
	}
	
	private void initMap(){
		mapRootNode = new Node();
		
		
		tileMap = new TileMap(100, 100);
	 	 
		Material brickMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		brickMaterial.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
		
		Material rockMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		rockMaterial.setTexture("ColorMap", assetManager.loadTexture("textures/ConcreteTexture.png"));
		
		Quad tileQuad = new Quad(1.0f,1.0f);
		
		for(int i=0; i<tileMap.xSize; i++){
			for(int j=0; j<tileMap.ySize; j++){
				Geometry tileGeom = new Geometry("Floor", tileQuad);
				tileGeom.move(i, j, 0);
				if(tileMap.getTile(i, j).type == 1)
					tileGeom.setMaterial(brickMaterial);
				else
					tileGeom.setMaterial(rockMaterial);
				
				mapRootNode.attachChild(tileGeom);
			}
		} 
		rootNode.attachChild(mapRootNode);
	}
	
	private void initPlayer(){
		player = new Player();
		
		Material playerMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		playerMaterial.setColor("Color", ColorRGBA.Red);
		Box b = new Box(player.position.add(0.0f, 0.0f, 0.5f), 0.5f, 0.5f, 0.5f);
		playerModel = new Geometry("Player", b);
		
		playerModel.setMaterial(playerMaterial);
		rootNode.attachChild(playerModel);
	}
	
	private void initKeys() {
		inputManager.addMapping("MovePlayer", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
		
		// Add the names to the action listener.
		inputManager.addListener(actionListener, new String[]{"MovePlayer"});
		 
	}
	
	private ActionListener actionListener = new ActionListener() {
		public void onAction(String name, boolean keyPressed, float tpf) {
			if (name.equals("MovePlayer")) {
				if(keyPressed){
					mouseActive = true;
				}
				else{
					mouseActive = false;
					player.setState(PlayerState.IDLE);
				}
			}
		}
	};

	@SuppressWarnings("unused")
	private AnalogListener analogListener = new AnalogListener() {
		public void onAnalog(String name, float value, float tpf) {
			
		}
	};


	void updateCamera(){
		Vector3f newCameraPosition = player.position.add(-6.0f, -6.0f, 10.0f);
		cam.setLocation(newCameraPosition);
		cam.lookAt(player.position, Vector3f.UNIT_Z);
	}
	
	void updateDrawEntities(){
		playerModel.setLocalTranslation(player.position);
		Matrix3f rotationMat = new Matrix3f();
		rotationMat.fromStartEndVectors(Vector3f.UNIT_Y, player.direction.normalize());
		playerModel.setLocalRotation(rotationMat);;
	}

	void handleInputs(){
	
		if(mouseActive){
			Vector3f mouseOrigin = cam.getLocation();
			Vector2f mouseScreenCoord = inputManager.getCursorPosition();
			Vector3f mouseWorldCoord = cam.getWorldCoordinates(mouseScreenCoord, 0.1f);
			Vector3f mouseDirection = mouseWorldCoord.subtract(mouseOrigin).normalizeLocal();
			
			Ray ray = new Ray(mouseOrigin, mouseDirection);
			CollisionResults collided = new CollisionResults();
			mapRootNode.collideWith(ray, collided);
			
			// NB: This results in false negatives due to edge collision errors.
			//     Need to be replaced with a more general z=0 plane collision
			
			if(collided.size()>0){
				CollisionResult closestCollision = collided.getClosestCollision();
				mouseIndicator.setLocalTranslation(closestCollision.getContactPoint());
				player.direction = closestCollision.getContactPoint().subtract(player.position);
				player.setState(PlayerState.RUNNING);
			}
			else{
				player.setState(PlayerState.IDLE);
			}
		}
	}
}