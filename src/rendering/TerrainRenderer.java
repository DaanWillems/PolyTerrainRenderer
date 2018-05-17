package rendering;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import org.joml.Matrix4f;
import org.joml.Vector3f;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setMatrices(Terrain terrain) {
	    projectionMatrix = Transformation.getProjectionMatrix();
	    
	    worldMatrix = terrain.getWorldMatrix();
	    
		shader.setUniform("worldMatrix", worldMatrix);
		shader.setUniform("projectionMatrix", projectionMatrix);
	}
	
	public void render(Terrain terrain) {
		shader.start();
		setMatrices(terrain);
		
	    glBindVertexArray(terrain.getVaoId());
	    glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, terrain.getVertexCount());
	    
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		
		shader.stop();
	}
}
