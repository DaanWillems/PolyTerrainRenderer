package object;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Mesh {
	
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	private int vertexCount;
	private int vaoId;
	private Matrix4f worldMatrix;
	
	public Mesh() {
		
		position = new Vector3f(0f, 0f, 0f);
		rotation = new Vector3f(0f, 0f, 0f);
		
		worldMatrix = new Matrix4f();
	}
	
	public Matrix4f getWorldMatrix() {
        worldMatrix.identity().translate(position).
	        rotateX((float)Math.toRadians(rotation.x)).
	        rotateY((float)Math.toRadians(rotation.y)).
	        rotateZ((float)Math.toRadians(rotation.z)).
	        scale(scale);
        return worldMatrix;
	}

	public int getVaoId() {
		return vaoId;
	}

	public void setVaoId(int vaoId) {
		this.vaoId = vaoId;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
}