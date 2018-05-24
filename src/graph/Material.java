package graph;

import org.joml.Vector4f;

public class Material {
	
    public static final Vector4f DEFAULT_COLOUR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	
    private Vector4f diffuseColour;
    private Texture texture;

	public Material() {
        this.diffuseColour = DEFAULT_COLOUR;
    }
    
    public Vector4f getDiffuseColour() {
		return diffuseColour;
	}

	public void setDiffuseColour(Vector4f diffuseColour) {
		this.diffuseColour = diffuseColour;
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public boolean hasTexture() {
		if(texture == null) {
			return false;
		}
		return true;
	}
}
