package dam.pmdm.personajesmario;

import androidx.recyclerview.widget.RecyclerView;

import dam.pmdm.personajesmario.databinding.CharacterCardviewBinding;

/**
 * ViewHolder que contiene las vistas de un personaje dentro de un RecyclerView.
 * Se encarga de vincular los datos del personaje con las vistas de su correspondiente tarjeta.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final CharacterCardviewBinding binding; // Binding para las vistas de cada tarjeta

    /**
     * Constructor del ViewHolder.
     * @param binding El binding de la vista de la tarjeta.
     */
    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Vincula los datos de un personaje con las vistas de la tarjeta.
     * @param character El objeto CharacterData que contiene los datos del personaje.
     */
    public void bind(CharacterData character) {
        // Establece la imagen del personaje
        binding.image.setImageResource(character.getImage());

        // Establece el nombre del personaje
        binding.name.setText(character.getName());

        // Asegura que los cambios se apliquen de inmediato en el layout
        binding.executePendingBindings();
    }
}
