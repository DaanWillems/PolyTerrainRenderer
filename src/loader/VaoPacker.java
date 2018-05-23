package loader;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;

import static org.lwjgl.opengl.ARBFragmentShader.*;
import static org.lwjgl.opengl.ARBShaderObjects.*;
import static org.lwjgl.opengl.ARBVertexBufferObject.*;
import static org.lwjgl.opengl.ARBVertexShader.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
 
import org.lwjgl.BufferUtils;
import org.lwjgl.assimp.AIVector3D;
import org.lwjgl.assimp.AIVector3D.Buffer;
import org.lwjgl.system.MemoryUtil;

public class VaoPacker {
	
	private ArrayList<Integer> vaos = new ArrayList<Integer>();

	public static int loadToVao(float[] vertices) {
		int vaoId = createVao();
		packVbo(0, 3, vertices);
		return vaoId;
	}
	
	public static int loadMeshToVao(float[] vertices, int[] indices, float[] normals) {
		int vaoId = createVao();
		packVbo(0, 3, vertices);
		packVbo(1, 3, normals);
		packIndices(indices);
		return vaoId;
	}
	
	public static int loadMeshToVao(float[] vertices, int[] indices, float[] normals, float[] texCoords) {
		int vaoId = createVao();
		packVbo(0, 3, vertices);
		packVbo(1, 3, normals);
		packVbo(2, 2, texCoords);
		packIndices(indices);
		return vaoId;
	}
	
	public static int loadTerrainToVao(float[] vertices, float[] normals, float[] colours) {
		int vaoId = createVao();
		packVbo(0, 3, vertices);
		packVbo(1, 3, normals);
		packVbo(2, 3, colours);
		return vaoId;
	}
	
	public static int createVao() {
		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);
		return vaoId;
	}
	
	public static void packVbo(int pointer, int interval, float[] meshData) {
		int vboId = glGenBuffers();
		FloatBuffer MeshBuffer = MemoryUtil.memAllocFloat(meshData.length);
		MeshBuffer.put(meshData).flip();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, MeshBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(pointer, interval, GL_FLOAT, false, 0, 0);
	}
	
	public static void packIndices(int[] meshData) {
		int idxVboId = glGenBuffers();
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(meshData.length);
		indicesBuffer.put(meshData).flip();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
	}
	
	public static void packIndices(IntBuffer meshData) {
		int idxVboId = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER_ARB, idxVboId);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER_ARB, meshData,
                GL_STATIC_DRAW_ARB);
	}
}
