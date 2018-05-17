package shaders;

public class TerrainShader extends ShaderProgram{
	
	private static final String VERTEX_FILE ="src/shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE ="src/shaders/terrainFragmentShader.txt";

	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
