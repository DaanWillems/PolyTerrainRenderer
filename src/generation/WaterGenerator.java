package generation;

import loader.VaoPacker;
import object.Terrain;
import object.Water;

public class WaterGenerator {

	public Water generateWater() {
		
		float[] vertices = new float[] {
			 0f,   -2f, -0.5f,
			-0.5f, -2f,   0f,
			 0.5f, -2f,   0f,
		};
		
		Water w = new Water();
		w.setVertexCount(vertices.length);
		int vaoId = VaoPacker.loadToVao(vertices);
		w.setVaoId(vaoId);
		
		return w;
	}
}
