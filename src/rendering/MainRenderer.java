package rendering;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import object.Scene;
import shaders.TerrainShader;

public class MainRenderer {
	
	GuiRenderer guiRenderer;
	MeshRenderer meshRenderer;
	TerrainRenderer terrainRenderer;
	WaterRenderer waterRenderer;
	Scene scene;
		
	public MainRenderer(Scene scene) {
		this.scene = scene;
		
		terrainRenderer = new TerrainRenderer();
	}
	
	public void render() {
		prepare();
		doMainRenderPass();
	}
	
	private void doMainRenderPass() {
		terrainRenderer.render(scene.terrain);
//		waterRenderer.render(scene.water);
//		meshRenderer.render(scene.meshes);
	}
	
	//Prepare screen for new render
	private void prepare() {
		glClearColor(0.3f, 0.3f, 0.3f, 1);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
}
