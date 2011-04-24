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
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;

import com.jme3.math.*;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;


import com.jme3.material.Material;

import juggernaut.TileMap;
/**
 * Example 9 - How to make walls and floors solid.
 * This version uses Physics and a custom Action Listener.
 * @author normen, with edits by Zathras
 */
public class MainGame extends SimpleApplication
  implements ActionListener {
 
 
  public static void main(String[] args) {
    MainGame app = new MainGame();
    app.start();
  }
 
 public void simpleInitApp() {
    /** Set up Physics */
    // We re-use the flyby camera for rotation, while positioning is handled by physics
    viewPort.setBackgroundColor(new ColorRGBA(0.7f,0.8f,1f,1f));
    flyCam.setMoveSpeed(10);
    flyCam.setUpVector(new Vector3f(0.0f, 0.0f, 1.0f));
    cam.lookAtDirection(new Vector3f(0.0f, 1.0f, 0.0f), 
    					new Vector3f(0.0f, 0.0f, 1.0f));

	TileMap tileMap = new TileMap(200, 100);
 
 
	Material brickMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	brickMaterial.setTexture("ColorMap",
	            assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
	
	Material rockMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	
//	rockMaterial.setTexture("ColorMap",
//    assetManager.loadTexture("Textures/Terrain/Rock/Rock.PNG"));
	rockMaterial.setTexture("ColorMap",
      assetManager.loadTexture("textures/ConcreteTexture.png"));
	
	Quad tileQuad = new Quad(1.0f,1.0f);
	
    for(int i=0; i<tileMap.xSize; i++){
    	for(int j=0; j<tileMap.ySize; j++){
    		//Box b  = new Box(new Vector3f(i, 0, j), 0.5f, 0.5f, 0.5f);
    		
    		//Geometry geom = new Geometry(String.valueOf(i)+" "+String.valueOf(j), b);
    		Geometry tileGeom = new Geometry("Floor", tileQuad);
    		
    		tileGeom.move(i, j, 0);
    		if(tileMap.getTile(i, j).type == 1)
    			tileGeom.setMaterial(brickMaterial);
    		else
    			tileGeom.setMaterial(rockMaterial);
    		
    		rootNode.attachChild(tileGeom);
    	}
    }

 
  }

	@Override
	public void onAction(String arg0, boolean arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
}