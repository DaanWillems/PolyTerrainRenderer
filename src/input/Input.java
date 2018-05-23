package input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Q;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_U;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import java.sql.Time;
import java.util.Date;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;

import org.joml.Vector2f;
import org.joml.Vector3f;

import graph.Scene;
import rendering.DisplayManager;
import rendering.MainRenderer;

public class Input {

	private static final float MOUSE_SENSITIVITY = 0.2f;
	private static Vector3f cameraInc;
	private static final float CAMERA_POS_STEP = 0.2f;

	private MouseInput mouseInput;
	
	private Date lastAction;
	private Scene scene;

	public Input(Scene scene, MouseInput mouseInput) {
		this.scene = scene;
		this.mouseInput = mouseInput;
		cameraInc = new Vector3f();
		lastAction = new Date();
	}

	public void input() {
		cameraInc.set(0, 0, 0);
		if (DisplayManager.isKeyPressed(GLFW_KEY_W)) {
			cameraInc.z = -1;
		} else if (DisplayManager.isKeyPressed(GLFW_KEY_S)) {
			cameraInc.z = 1;
		}
		if (DisplayManager.isKeyPressed(GLFW_KEY_A)) {
			cameraInc.x = -1;
		} else if (DisplayManager.isKeyPressed(GLFW_KEY_D)) {
			cameraInc.x = 1;
		}
		if (DisplayManager.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
			cameraInc.y = -1;
		} else if (DisplayManager.isKeyPressed(GLFW_KEY_SPACE)) {
			cameraInc.y = 1;
		}
		
		scene.camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP,
				cameraInc.z * CAMERA_POS_STEP);

		if (mouseInput.isLeftButtonPressed()) {
			Vector2f rotVec = mouseInput.getDisplVec();
			scene.camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
		}
		mouseInput.input();
	}
	
	private boolean checkCooldown() {
		Date now = new Date();
		long diff = now.getTime() - lastAction.getTime();
		
		lastAction = new Date();
		System.out.println(diff);
		
		return diff > 100;
	}
}
