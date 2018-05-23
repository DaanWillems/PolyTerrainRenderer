package graph;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import loader.TextureLoader;

import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {
	
    private final int id;

    public Texture(String fileName) throws Exception {
        id = TextureLoader.loadTexture(fileName);
    }

    public Texture(int id) {
        this.id = id;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public int getId() {
        return id;
    }
    
    public void cleanup() {
        glDeleteTextures(id);
    }
	
}
