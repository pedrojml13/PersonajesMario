package com.personajesmario;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.personajesmario.databinding.CharacterListFragmentBinding;
import java.util.ArrayList;

/**
 * Fragmento que muestra una lista de personajes en un RecyclerView.
 * Utiliza el layout CharacterListFragmentBinding para configurar y mostrar los datos de los personajes.
 */
public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding; // Binding para el layout
    private ArrayList<CharacterData> characters; // Lista de personajes
    private CharacterRecyclerViewAdapter adapter; // Adaptador del RecyclerView

    /**
     * Infla el layout para este fragmento y lo vincula a los elementos de la interfaz.
     * @param inflater El objeto que se utiliza para inflar el layout.
     * @param container El contenedor en el que se incluirá el fragmento.
     * @param savedInstanceState El estado guardado, si existe.
     * @return La vista inflada del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout utilizando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método que se llama después de que la vista del fragmento ha sido creada.
     * Aquí se inicializa la lista de personajes y se configura el RecyclerView.
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado guardado, si existe.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de personajes
        loadCharacters(); // Cargar los personajes

        // Configurar el RecyclerView para mostrar los personajes
        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.charactersRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.charactersRecyclerview.setAdapter(adapter);
    }

    /**
     * Método para cargar los datos de los personajes en la lista.
     * Aquí se añaden los personajes a la lista 'characters' con sus respectivas imágenes y descripciones.
     */
    private void loadCharacters() {
        characters = new ArrayList<CharacterData>();

        // Añadir personajes a la lista
        characters.add(new CharacterData(
                R.drawable.mario,
                getString(R.string.mario), getString(R.string.mario_description), getString(R.string.mario_abilities)
        ));

        characters.add(new CharacterData(
                R.drawable.luigi,
                getString(R.string.luigi), getString(R.string.luigi_description), getString(R.string.luigi_abilities)
        ));

        characters.add(new CharacterData(
                R.drawable.peach,
                getString(R.string.peach), getString(R.string.peach_description), getString(R.string.peach_abilities)
        ));

        characters.add(new CharacterData(
                R.drawable.toad,
                getString(R.string.toad), getString(R.string.toad_description), getString(R.string.toad_abilities)
        ));
    }

    /**
     * Método que se llama cuando el fragmento empieza.
     * Cambia el título del ActionBar de la actividad asociada.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.character_list);
        }
    }
}
