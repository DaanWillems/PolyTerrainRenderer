package loader;

import java.io.File;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.assimp.*;
import org.lwjgl.assimp.AIMaterial;

import graph.Material;
import graph.Mesh;

import static org.lwjgl.assimp.Assimp.*;

import util.Utils;

public class StaticMeshLoader {
	private static ArrayList meshes;

	public static Mesh[] load(String resourcePath, String texturesDir) throws Exception {
        File file = new File(resourcePath);
        // Assimp will be able to find the corresponding mtl file if we call aiImportFile this way.
        AIScene scene = aiImportFile(file.getAbsolutePath(), aiProcess_GenSmoothNormals |
        		aiProcess_JoinIdenticalVertices | aiProcess_Triangulate | aiProcess_FixInfacingNormals);
        
        if (scene == null) {
            throw new Exception("Error loading model");
        }
        
        
        int numMaterials = scene.mNumMaterials();
        Material[] materials = new Material[numMaterials];
        System.out.println(numMaterials);

        PointerBuffer aiMaterials = scene.mMaterials();
        for (int i = 0; i < numMaterials; i++) {
            AIMaterial aiMaterial = AIMaterial.create(aiMaterials.get(i));
            
            AIColor4D colour = AIColor4D.create();
            int result = aiGetMaterialColor(aiMaterial, AI_MATKEY_COLOR_DIFFUSE, aiTextureType_NONE, 0,
                    colour);
            
            Material material = new Material();
            
            System.out.println(new Vector4f(colour.r(), colour.g(), colour.b(), colour.a()));
            material.setDiffuseColour(new Vector4f(colour.r(), colour.g(), colour.b(), colour.a()));
            materials[i] = material;
        }
        
        
        int numMeshes = scene.mNumMeshes();
        System.out.println("Meshes: "+numMeshes);
        PointerBuffer aiMeshes = scene.mMeshes();
        Mesh[] meshes = new Mesh[numMeshes];
        for (int i = 0; i < numMeshes; i++) {
            AIMesh aiMesh = AIMesh.create(aiMeshes.get(i));
            Mesh mesh = processMesh(aiMesh, materials[aiMesh.mMaterialIndex()]);
            meshes[i] = mesh;
        }

        return meshes;
    }

	private static Mesh processMesh(AIMesh aiMesh, Material material) {
		List<Float> vertices = new ArrayList<>();
		List<Float> textures = new ArrayList<>();
		List<Float> normals = new ArrayList<>();
		List<Integer> indices = new ArrayList();
		
		processVertices(aiMesh, vertices);
		processNormals(aiMesh, normals);
		processTextCoords(aiMesh, textures);
		processIndices(aiMesh, indices);
		
		Mesh mesh = new Mesh();
		
        int vaoId = VaoPacker.loadMeshToVao(Utils.listToArray(vertices), Utils.listIntToArray(indices), Utils.listToArray(normals), Utils.listToArray(textures));
        mesh.setMaterial(material);
        mesh.setVaoId(vaoId);
        mesh.setVertexCount(Utils.listIntToArray(indices).length);

		return mesh;
	}
	
	protected static void processVertices(AIMesh aiMesh, List<Float> vertices) {
        AIVector3D.Buffer aiVertices = aiMesh.mVertices();
        while (aiVertices.remaining() > 0) {
            AIVector3D aiVertex = aiVertices.get();
            vertices.add(aiVertex.x());
            vertices.add(aiVertex.y());
            vertices.add(aiVertex.z());
        }
    }
	
    protected static void processNormals(AIMesh aiMesh, List<Float> normals) {
        AIVector3D.Buffer aiNormals = aiMesh.mNormals();
        while (aiNormals != null && aiNormals.remaining() > 0) {
            AIVector3D aiNormal = aiNormals.get();
            normals.add(aiNormal.x());
            normals.add(aiNormal.y());
            normals.add(aiNormal.z());
        }
    }
    
    protected static void processTextCoords(AIMesh aiMesh, List<Float> textures) {
        AIVector3D.Buffer textCoords = aiMesh.mTextureCoords(0);
        int numTextCoords = textCoords != null ? textCoords.remaining() : 0;
        System.out.println("test: "+numTextCoords);
        for (int i = 0; i < numTextCoords; i++) {
            AIVector3D textCoord = textCoords.get();
            textures.add(textCoord.x());
            textures.add(1 - textCoord.y());
        }
    }
    
    protected static void processIndices(AIMesh aiMesh, List<Integer> indices) {
        int numFaces = aiMesh.mNumFaces();
        AIFace.Buffer aiFaces = aiMesh.mFaces();
        for (int i = 0; i < numFaces; i++) {
            AIFace aiFace = aiFaces.get(i);
            IntBuffer buffer = aiFace.mIndices();
            while (buffer.remaining() > 0) {
                indices.add(buffer.get());
            }
        }
    }
}
