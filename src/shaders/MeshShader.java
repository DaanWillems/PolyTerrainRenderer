package shaders;

public class MeshShader extends ShaderProgram{
	
	private static final String VERTEX_FILE ="src/shaders/meshVertexShader.txt";
	private static final String FRAGMENT_FILE ="src/shaders/meshFragmentShader.txt";

	public MeshShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
