package generation;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.joml.Vector3f;

import generation.PerlinNoise;
import loader.VaoPacker;
import object.Terrain;

public class TerrainGenerator{

	private ArrayList<Float> positions = new ArrayList<Float>();
	
	private ArrayList<Float> colours = new ArrayList<Float>();
	
	private ArrayList<Float> normals = new ArrayList<Float>();
	
	private float[][] heights = new float[5][5];

	public Terrain generateTerrain() {
		
		PerlinNoise perlinNoise= new PerlinNoise(4, 12, 0.55f); 
	    heights = new float[100][100];
		for (int z = 0; z < heights.length; z++) {
			for (int x = 0; x < heights[z].length; x++) {
				heights[z][x] = perlinNoise.getPerlinNoise(x, z);
//				heights[z][x] = 1;
			}
		}
		
//		heights[2][2] = 3;
		
		for(int z = 0; z < heights.length; z++) {
			for(int x = 0; x < heights[z].length; x++) {
				if(heights.length-1 == z) {
					continue;
				}
				if(heights[z].length-1 == x) {
					continue;
				}
				
				Vector3f topLeft = new Vector3f(x, heights[z][x], z);
				Vector3f topRight = new Vector3f(x+1, heights[z][x+1], z);
				Vector3f bottomLeft = new Vector3f(x, heights[z+1][x], z+1);
				Vector3f bottomRight = new Vector3f(x+1, heights[z+1][x+1], z+1);
				

				packTriangle(topLeft, bottomLeft, topRight);
				packTriangle(topRight, bottomLeft, bottomRight);
			}
		}

		Terrain t = new Terrain();
		t.setVertexCount(positions.size());
		int vaoId = VaoPacker.loadToVao(toArray(positions), toArray(normals), toArray(colours));
		t.setVaoId(vaoId);
		
		return t;
	}
	
	public void packTriangle(Vector3f v1, Vector3f v2, Vector3f v3) {		
		positions.add(v1.x-25);
		positions.add(v1.y);
		positions.add(v1.z-25);
		
		positions.add(v2.x-25);
		positions.add(v2.y);
		positions.add(v2.z-25);
		
		positions.add(v3.x-25);
		positions.add(v3.y);
		positions.add(v3.z-25);
		
		Vector3f n1 = new Vector3f(v1.x, v1.y, v1.z);
		Vector3f n2 = new Vector3f(v2.x, v2.y, v2.z);
		Vector3f n3 = new Vector3f(v3.x, v3.y, v3.z);
		
		//Calc normal
		Vector3f dir = (n2.sub(n1)).cross((n3.sub(n1)));
		Vector3f norm = dir.normalize();
				
		normals.add(norm.x);
		normals.add(norm.y);
		normals.add(norm.z);
		
		normals.add(norm.x);
		normals.add(norm.y);
		normals.add(norm.z);
		
		normals.add(norm.x);
		normals.add(norm.y);
		normals.add(norm.z);
		
		Vector3f colour = null;
		
		int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
		float colourOffset = randomNum/20f;
		
		int randomNum2 = ThreadLocalRandom.current().nextInt(0, 1 + 1);
		float colourOffset2 = randomNum2/20f;
		
		if(v1.y > 1.5) {
			colour = new Vector3f(0.56f+colourOffset, 0.5f, 0.56f+colourOffset2);
		} else {
			colour = new Vector3f(0.25f+colourOffset, 0.5f, 0.25f+colourOffset2);
		}
		
		colours.add(colour.x);
		colours.add(colour.y);
		colours.add(colour.z);
		
		colours.add(colour.x);
		colours.add(colour.y);
		colours.add(colour.z);
		
		colours.add(colour.x);
		colours.add(colour.y);
		colours.add(colour.z);
	}
	
	public float[] toArray(ArrayList<Float> list) {
		float[] newArr = new float[list.size()];
		
	    for (int i=0; i < newArr.length; i++) {
	    	newArr[i] = list.get(i);
	    }
	    
	    return newArr;
	}
}
