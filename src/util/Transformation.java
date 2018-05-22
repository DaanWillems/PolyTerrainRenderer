package util;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import rendering.DisplayManager;

public class Transformation {

	private static float FOV = (float) Math.toRadians(60.0f);
	private static float Z_NEAR = 0.01f;
	private static float Z_FAR = 1000.f;
	
    private static Matrix4f projectionMatrix = new Matrix4f();
	private static Matrix4f worldMatrix = new Matrix4f();
    
    public Transformation() {
        projectionMatrix = new Matrix4f();
    }

    public static Matrix4f getProjectionMatrix() {
        float aspectRatio = (float)DisplayManager.getWidth() / (float)DisplayManager.getHeight();        
        projectionMatrix.identity();
        projectionMatrix.perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
        return projectionMatrix;
    }
}