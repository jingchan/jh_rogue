package juggernaut;
 
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.MouseButtonTrigger;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;

import com.jme3.math.*;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;
import com.jme3.input.ChaseCamera;

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
	private ChaseCamera chaseCam;
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
		flyCam.setEnabled(false);
//	    flyCam.setMoveSpeed(10);
//	    flyCam.setUpVector(new Vector3f(0.0f, 0.0f, 1.0f));
//	    flyCam.setDragToRotate(true);
//	    cam.lookAtDirection(new Vector3f(0.0f, 1.0f, 0.0f), 
//	    					new Vector3f(0.0f, 0.0f, 1.0f));
//	    cam.setLocation(location)
//	    chaseCam = new ChaseCamera(cam, playerModel, inputManager);
//	    chaseCam.setUpVector(new Vector3f(0.0f, 0.0f, 1.0f));
	    
	    //chaseCam.setLookAtOffset(new Vector3f(0, 0, 4));
	    
//        chase_cam.setDragToRotate(false);
//        chase_cam.setInvertVerticalAxis(true);
//        chase_cam.setLookAtOffset(new Vector3f(0, 2f, 0));

		cam.getLocation().set(0.0f, 0.0f, 0.0f);
		//cam.getLocation().set(0.0f, 0.0f, 0.0f);
		cam.getUp().set(0.0f,0.0f,1.0f);
		cam.getLeft().set(-1.0f,0.0f,0.0f);
		//cam.setParallelProjection(true);
		System.out.print(cam.toString());
		

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

	private AnalogListener analogListener = new AnalogListener() {
		public void onAnalog(String name, float value, float tpf) {
//			if(name.equals("MovePlayer")){
////					Vector3f v = player.getLocalTranslation();
////					player.setLocalTranslation(v.x - value*speed, v.y, v.z);
//				
//
//			}
		}
	};


	void updateCamera(){
		Vector3f newCameraPosition = player.position.add(-6.0f, -6.0f, 10.0f);
		cam.setLocation(newCameraPosition);
		cam.lookAt(player.position, Vector3f.UNIT_Z);
	
//		Vector3f cameraLocation = cam.getLocation();
//		System.out.printf("CamLocation: %f, %f, %f\n", cameraLocation.x, cameraLocation.y, cameraLocation.z);
//		System.out.printf("CamTarget: %f, %f, %f\n", player.position.x, player.position.y, player.position.z);
	}
	
	void updateDrawEntities(){
		playerModel.setLocalTranslation(player.position);
		
//		System.out.printf(String.valueOf(player.position.x) + " ");
//		System.out.printf(String.valueOf(player.position.y) + " ");
//		System.out.printf(String.valueOf(player.position.z) + "\n");
	}

	void handleInputs(){
	
		if(mouseActive){
			Vector2f mousePosition = inputManager.getCursorPosition();
			Vector3f mouseOrigin = cam.getWorldCoordinates(mousePosition, 0.0f);
			Vector3f mouseWorldCoord = cam.getWorldCoordinates(mousePosition, 0.999f);
			Vector3f mouseDirection = mouseWorldCoord.subtract(mouseOrigin).normalizeLocal();
			System.out.printf("Cam:\t%f\t%f\t%f\n", cam.getLocation().x, cam.getLocation().y, cam.getLocation().z);
			System.out.printf("MouseOrigin:\t%f\t%f\t%f\n", mouseOrigin.x, mouseOrigin.y, mouseOrigin.z);
			System.out.printf("Mouse:\t%f\t%f\t%f\n", mouseWorldCoord.x, mouseWorldCoord.y, mouseWorldCoord.z);
			
			//Ray ray = new Ray(cam.getLocation(), mouseWorldCoord);
			Ray ray = new Ray(mouseOrigin, mouseDirection);
			System.out.printf("limit:%f\n", ray.limit);
			CollisionResults collided = new CollisionResults();
			mapRootNode.collideWith(ray, collided);
			
			
			System.out.printf("Collision Size: %d\n", collided.size());
			if(collided.size()>0){
				CollisionResult closestCollision = collided.getClosestCollision();
				mouseIndicator.setLocalTranslation(closestCollision.getContactPoint());
				//System.out.printf("%f, %f, %f\n", closestCollision.getContactPoint().x, closestCollision.getContactPoint().y, closestCollision.getContactPoint().z);
				player.direction = closestCollision.getContactPoint().subtract(player.position);
				player.setState(PlayerState.RUNNING);
			}
			else
				player.setState(PlayerState.IDLE);
		}
	}
}