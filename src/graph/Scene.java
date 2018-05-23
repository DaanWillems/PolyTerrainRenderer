package graph;

import java.util.ArrayList;

public class Scene {
		
	public Terrain terrain;
	public Water water;
	public ArrayList<GuiElement> guiElements;
	public ArrayList<Mesh> meshes;
	public Camera camera;
	
	public Scene() {
		camera = new Camera();
		guiElements = new ArrayList<>();
		meshes = new ArrayList<>();
	}
	
}
