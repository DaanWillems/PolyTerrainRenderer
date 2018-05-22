package rendering;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import org.joml.Matrix4f;

import object.Scene;
import object.Terrain;
import object.Water;
import shaders.TerrainShader;
import util.Transformation;

public class WaterRenderer {
	TerrainShader shader;
	private Matrix4f projectionMatrix;
	private Matrix4f worldMatrix;
	
	public WaterRenderer() {
		
		shader = new TerrainShader();
		try {
			shader.createUniform("projectionMatrix");
			shader.createUniform("worldMatrix");
			shader.createUniform("viewMatrix");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setMatrices(Scene scene) {
	    projectionMatrix = Transformation.getProjectionMatrix();
	    
	    worldMatrix = scene.water.getWorldMatrix();
	    
	    shader.setUniform("viewMatrix", scene.camera.getViewMatrix());
		shader.setUniform("worldMatrix", worldMatrix);
		shader.setUniform("projectionMatrix", projectionMatrix);
	}
	
	public void render(Scene scene) {
		if(scene.water == null) {
			return;
		}
		
		shader.start();
		setMatrices(scene);
		
	    glBindVertexArray(scene.water.getVaoId());
	    glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, scene.water.getVertexCount());
	    
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		shader.stop();
	}
}
