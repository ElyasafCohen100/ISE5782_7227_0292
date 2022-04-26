package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

//================== Scene class (PDS - Plain Data Structure) ==================//
public class Scene {

    //==== we use with design pattern called "builder pattern" ====//
    private final String name; // the scene's name
    private final Color background; // the background's color (black is the default)
    private final AmbientLight ambientLight; //the ambientLight - תאורה סביבתית
    private final Geometries geometries; // the 3D model

    public String getName() {
        return name;
    }
    public Color getBackground() {
        return background;
    }
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }
    public Geometries getGeometries() {
        return geometries;
    }

    private Scene(SceneBuilder builder) {
        this.name = builder.name;
        this.background = builder.background;
        this.ambientLight = builder.ambientLight;
        this.geometries = builder.geometries;
    }

    //================== SceneBuilder class ==================//
    public static class SceneBuilder {
        private final String name; // the scene's name
        private Color background; // the background's color (black is the default)
        private AmbientLight ambientLight; //the ambientLight
        private Geometries geometries; // the 3D model

        public SceneBuilder(String name){
            this.name = name;
        }

        //========= chaining (שירשור) method =========//

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public Scene build(){return new Scene(this);}
    }
}