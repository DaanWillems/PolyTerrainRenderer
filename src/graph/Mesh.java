package graph;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import util.Transformation;

public class Mesh {
	
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	private int vertexCount;
	private int vaoId;
	private Matrix4f worldMatrix;
	private Texture texture;
	private Material material;
	
	public Mesh() {
		position = new Vector3f(0f, 0f, 0f);
		rotation = new Vector3f(0f, 0f, 0f);
		material = new Material();
		worldMatrix = new Matrix4f();
		try {
			texture = new Texture("res/models/test.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Matrix4f getWorldMatrix() {
		 worldMatrix.identity().translate(getPosition()).
	         rotateX((float)Math.toRadians(rotation.x)).
	         rotateY((float)Math.toRadians(rotation.y)).
	         rotateZ((float)Math.toRadians(rotation.z)).
	         scale(scale);
		 return worldMatrix;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public float getScale() {
		return scale;
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
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	
	public void move(float x, float y, float z) {
		position = position.add(new Vector3f(x, y, z));
	}
	
	public void rotate(float x, float y, float z) {
		rotation = rotation.add(new Vector3f(x, y, z));
	}
	
	public void scale(float scale) {
		this.scale = scale;
	}
}
