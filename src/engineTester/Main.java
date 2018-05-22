package engineTester;

import org.joml.Vector3f;

import generation.TerrainGenerator;
import generation.WaterGenerator;
import input.Input;
import input.MouseInput;
import loader.VaoPacker;
import object.Mesh;
import object.Scene;
import object.Terrain;
import object.Water;
import rendering.DisplayManager;
import rendering.MainRenderer;
import util.OBJLoader;

public class Main {

	private static MouseInput mouseInput;

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		float i = 0f;
		Scene scene = new Scene();
		
		Mesh mesh = null;
				
		try {
			mesh = OBJLoader.loadMesh("res/cube.obj");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mesh.move(20f, -2f, 20f);
		mesh.scale(4f);

		scene.meshes.add(mesh);
		
		Terrain terrain = new TerrainGenerator().generateTerrain();
		
		terrain.move(20f, 0f, 0f);
		terrain.scale(1f);
		
		scene.terrain = terrain;
		
		MainRenderer mr = new MainRenderer(scene);
		
		mouseInput = new MouseInput();
		Input input = new Input(scene, mouseInput);
		mouseInput.init();
		
		while(!DisplayManager.shouldClose()) {
			DisplayManager.updateDisplay();
			mr.render();
			input.input();
			mesh.move(0.011f, 0f, 0f);
//			mesh.rotate(0, 10f, 0f);
		}
	}
}
