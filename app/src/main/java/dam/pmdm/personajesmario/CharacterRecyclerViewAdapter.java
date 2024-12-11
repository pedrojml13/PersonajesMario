package dam.pmdm.personajesmario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import dam.pmdm.personajesmario.databinding.CharacterCardviewBinding;
import android.view.View;

/**
 * Adaptador para el RecyclerView que muestra una lista de personajes.
 * Este adaptador se encarga de enlazar los datos de los personajes a las vistas correspondientes.
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private final ArrayList<CharacterData> characters; // Lista de personajes
    private final Context context; // Contexto de la aplicación

    /**
     * Constructor del adaptador.
     * @param characters Lista de datos de personajes.
     * @param context Contexto de la aplicación para acceder a recursos y vistas.
     */
    public CharacterRecyclerViewAdapter(ArrayList<CharacterData> characters, Context context){
        this.characters = characters;
        this.context = context;
    }

    /**
     * Crea el ViewHolder para un elemento del RecyclerView.
     * @param parent El contenedor de la vista.
     * @param viewType Tipo de vista, usado para manejar diferentes tipos de elementos.
     * @return Un nuevo ViewHolder con el layout inflado.
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CharacterViewHolder(binding);
    }

    /**
     * Enlaza los datos de un personaje con el ViewHolder.
     * @param holder El ViewHolder que contiene las vistas.
     * @param position La posición del personaje en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        CharacterData currentCharacter = this.characters.get(position);
        holder.bind(currentCharacter);

        // Maneja el evento de clic
        holder.itemView.setOnClickListener(view -> characterClicked(currentCharacter, view));
    }

    /**
     * Devuelve el número total de elementos en la lista de personajes.
     * @return El tamaño de la lista de personajes.
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }


    /**
     * Maneja el evento de clic en un elemento de la lista.
     * Llama a la función characterClicked en la actividad principal.
     * @param currentCharacter El personaje que ha sido seleccionado.
     * @param view La vista que fue clickeada.
     */
    private void characterClicked(CharacterData currentCharacter, View view) {
        // Llama a la función characterClicked de MainActivity, pasando el personaje y la vista
        ((MainActivity) context).characerClicked(currentCharacter, view);
    }
}
