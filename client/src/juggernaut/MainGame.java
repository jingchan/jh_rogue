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
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Geometry;
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
    
	TileMap tileMap = new TileMap(30, 30);
 
 
	Material brickMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	brickMaterial.setTexture("ColorMap",
	            assetManager.loadTexture("Textures/Terrain/BrickWall/BrickWall.jpg"));
	Material rockMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	rockMaterial.setTexture("ColorMap",
	            assetManager.loadTexture("Textures/Terrain/Rock/Rock.PNG"));
	
//    mat.setColor("Color", ColorRGBA.Blue);
    for(int i=0; i<tileMap.xSize; i++)
    	for(int j=0; j<tileMap.ySize; j++){
    		Box b  = new Box(new Vector3f(i, 0, j), 0.5f, 0.5f, 0.5f);
    		//System.console().printf(String.valueOf(i).concat(" ").concat(String.valueOf(j)));
    		Geometry geom = new Geometry(String.valueOf(i)+" "+String.valueOf(j), b);
    		if(tileMap.getTile(i, j).type == 1)
    			geom.setMaterial(brickMaterial);
    		else
    			geom.setMaterial(rockMaterial);
    		
    		rootNode.attachChild(geom);
    	}
    		
//	Box b2  = new Box(Vector3f.ZERO, 1, 1, 1);
//	
//	Geometry geom2 = new Geometry("Box", b2);
//	Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//    mat2.setColor("Color", ColorRGBA.Blue);
//	geom2.setMaterial(mat2);
//	rootNode.attachChild(geom2);


 
  }

	@Override
	public void onAction(String arg0, boolean arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
}