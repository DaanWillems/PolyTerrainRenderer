package object;

import java.util.ArrayList;

public class Scene {
		
	public Terrain terrain;
	public Water water;
	public ArrayList<GuiElement> guiElements;
	public ArrayList<Mesh> meshes;
	
	public Scene() {
		guiElements = new ArrayList<>();
		meshes = new ArrayList<>();
	}
	
}
