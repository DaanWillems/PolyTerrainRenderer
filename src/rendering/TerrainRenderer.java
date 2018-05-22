package rendering;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import object.Scene;
import object.Terrain;
import shaders.TerrainShader;
import util.Transformation;

public class TerrainRenderer {
	
	TerrainShader shader;
	private Matrix4f projectionMatrix;
	private Matrix4f worldMatrix;
	
	public TerrainRenderer() {
		
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
	    
	    worldMatrix = scene.terrain.getWorldMatrix();
	    
	    shader.setUniform("viewMatrix", scene.camera.getViewMatrix());
		shader.setUniform("worldMatrix", worldMatrix);
		shader.setUniform("projectionMatrix", projectionMatrix);
	}
	
	public void render(Scene scene) {
		if(scene.terrain == null) {
			return;
		}
		
		shader.start();
		setMatrices(scene);
		
	    glBindVertexArray(scene.terrain.getVaoId());
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);
	    glEnableVertexAttribArray(2);

        glDrawArrays(GL_TRIANGLES, 0, scene.terrain.getVertexCount());
        
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
		
		shader.stop();
	}
}
