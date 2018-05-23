package rendering;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import java.util.ArrayList;

import org.joml.Matrix4f;

import graph.Mesh;
import graph.Scene;
import graph.Terrain;
import shaders.MeshShader;
import shaders.TerrainShader;
import util.Transformation;

public class MeshRenderer {
	
	MeshShader shader;
	private Matrix4f projectionMatrix;
	private Matrix4f worldMatrix;
	
	public MeshRenderer() {
		
		shader = new MeshShader();
		try {
			shader.createUniform("projectionMatrix");
			shader.createUniform("worldMatrix");
			shader.createUniform("viewMatrix");
			shader.createUniform("diffuseColour");
//			shader.createUniform("texture_sampler");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setMatrices(Mesh mesh, Scene scene) {
	    projectionMatrix = Transformation.getProjectionMatrix();
	    
	    worldMatrix = mesh.getWorldMatrix();
	    
	    shader.setUniform("viewMatrix", scene.camera.getViewMatrix());
		shader.setUniform("worldMatrix", worldMatrix);
		shader.setUniform("projectionMatrix", projectionMatrix);
		shader.setUniform("diffuseColour", mesh.getMaterial().getDiffuseColour());
//		shader.setUniform("texture_sampler", 0);
	}
	
	public void render(Scene scene) {
		shader.start();
		scene.meshes.forEach(mesh -> {
//			glActiveTexture(GL_TEXTURE0);
//			glBindTexture(GL_TEXTURE_2D, mesh.getTexture().getId());
			setMatrices(mesh, scene);
		    glBindVertexArray(mesh.getVaoId());
		    glEnableVertexAttribArray(0);
		    glEnableVertexAttribArray(1);
		    glEnableVertexAttribArray(2);
		    
		    glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
		    
		    glEnableVertexAttribArray(0);
		    glEnableVertexAttribArray(1);
		    glEnableVertexAttribArray(2);
		    
			glBindVertexArray(0);
		});
		
		shader.stop();
	}
}
