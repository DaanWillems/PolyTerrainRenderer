package engineTester;

import org.joml.Vector3f;

import generation.TerrainGenerator;
import generation.WaterGenerator;
import graph.Mesh;
import graph.Scene;
import graph.Terrain;
import graph.Water;
import input.Input;
import input.MouseInput;
import loader.StaticMeshLoader;
import loader.VaoPacker;
import rendering.DisplayManager;
import rendering.MainRenderer;
import util.OBJLoader;

public class Main {

	private static MouseInput mouseInput;

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Scene scene = new Scene();
		
		Mesh[] meshes = null;
				
		try {
			meshes = StaticMeshLoader.load("src/res/house.obj", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < meshes.length; i++) {
			Mesh mesh = meshes[i];
			scene.meshes.add(mesh);
			mesh.move(20f, 10f, 20f);
			mesh.scale(4f);
		}
		
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
//			mesh.move(0.011f, 0f, 0f);
//			mesh.rotate(0, 10f, 0f);
		}
	}
}
