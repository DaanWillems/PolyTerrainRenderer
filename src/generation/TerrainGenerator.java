package generation;

import loader.VaoPacker;
import object.Terrain;

public class TerrainGenerator {

	public Terrain generateTerrain() {
		
		float[] vertices = new float[] {
			 0f,   -2f, -0.5f,
			-0.5f, -2f,   0f,
			 0.5f, -2f,   0f,
		};
		
		Terrain t = new Terrain();
		t.setVertexCount(vertices.length);
		int vaoId = VaoPacker.packTerrain(vertices);
		t.setVaoId(vaoId);
		
		return t;
	}

}
