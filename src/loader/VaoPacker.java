package loader;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

public class VaoPacker {
	
	private ArrayList<Integer> vaos = new ArrayList<Integer>();

	public static int packTerrain(float[] vertices) {
		int vaoId = createVao();
		
		packVbo(0, 3, vertices);
		
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
}
