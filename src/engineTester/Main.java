package engineTester;

import generation.TerrainGenerator;
import object.Mesh;
import object.Scene;
import object.Terrain;
import rendering.DisplayManager;
import rendering.MainRenderer;

public class Main {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Scene scene = new Scene();
		Terrain terrain = new TerrainGenerator().generateTerrain();		
		scene.terrain = terrain;
		
		MainRenderer mr = new MainRenderer(scene);

		while(!DisplayManager.shouldClose()) {
			DisplayManager.updateDisplay();
			mr.render();
		}
	}
}
