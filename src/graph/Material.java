package graph;

import org.joml.Vector4f;

public class Material {
	
    public static final Vector4f DEFAULT_COLOUR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	
    private Vector4f diffuseColour;
    
	public Material() {
        this.diffuseColour = DEFAULT_COLOUR;
    }
    
    public Vector4f getDiffuseColour() {
		return diffuseColour;
	}

	public void setDiffuseColour(Vector4f diffuseColour) {
		this.diffuseColour = diffuseColour;
	}

}
