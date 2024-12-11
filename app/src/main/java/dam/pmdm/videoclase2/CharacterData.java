package dam.pmdm.videoclase2;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Representa los datos de un personaje, incluyendo su imagen, nombre, descripción y habilidades.
 */
public class CharacterData {

    // Atributos
    private final int image;
    private final String name;
    private final String description;
    private final String habilities;

    /**
     * Constructor para crear un objeto de tipo CharacterData.
     *
     * @param image        El recurso de imagen del personaje.
     * @param name         El nombre del personaje.
     * @param description  La descripción del personaje.
     * @param habilities   Las habilidades del personaje.
     */
    public CharacterData(int image, String name, String description, String habilities) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.habilities = habilities;
    }

    /**
     * Obtiene la imagen del personaje.
     *
     * @return El recurso de imagen del personaje.
     */
    public int getImage() {
        return image;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return La descripción del personaje.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Las habilidades del personaje.
     */
    public String getHabilities(){
        return habilities;
    }
}


