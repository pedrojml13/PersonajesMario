package dam.pmdm.personajesmario;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.pmdm.personajesmario.databinding.CharacterDetailFragmentBinding;

/**
 * Fragmento que muestra los detalles de un personaje.
 * Utiliza el layout CharacterDetailFragmentBinding para mostrar la información.
 */
public class CharacterDetailFragment extends Fragment {

    // Enlace a los componentes de la interfaz
    private CharacterDetailFragmentBinding binding;

    /**
     * Inflar el layout para este fragmento.
     * @param inflater El objeto que se usa para inflar el layout.
     * @param container El contenedor en el que se incluirá el fragmento.
     * @param savedInstanceState El estado guardado, si existe.
     * @return La vista inflada del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método que se llama después de que la vista del fragmento ha sido creada.
     * Aquí se asignan los datos a la interfaz.
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado guardado, si existe.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            int image = getArguments().getInt("image");
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String habilities = getArguments().getString("habilities");

            // Asignar los datos a los componentes de la interfaz
            binding.image.setImageResource(image);
            binding.name.setText(name);
            binding.description.setText(description);
            binding.abilities.setText(habilities);

            if(name == getString(R.string.mario)){
                binding.name.setTextColor(getResources().getColor(R.color.mario));
                binding.description.setBackgroundColor(getResources().getColor(R.color.mariodescription));
                binding.abilities.setBackgroundColor(getResources().getColor(R.color.marioabilities));
            }
            else if(name == getString(R.string.luigi)){
                binding.name.setTextColor(getResources().getColor(R.color.luigi));
                binding.description.setBackgroundColor(getResources().getColor(R.color.luigidescription));
                binding.abilities.setBackgroundColor(getResources().getColor(R.color.luigiabilities));
            }
            else if(name == getString(R.string.peach)){
                binding.name.setTextColor(getResources().getColor(R.color.peach));
                binding.description.setBackgroundColor(getResources().getColor(R.color.peachdescription));
                binding.abilities.setBackgroundColor(getResources().getColor(R.color.peachabilities));
            }
            else if(name == getString(R.string.toad)){
                binding.name.setTextColor(getResources().getColor(R.color.toad));
                binding.description.setBackgroundColor(getResources().getColor(R.color.toaddescription));
                binding.abilities.setBackgroundColor(getResources().getColor(R.color.toadabilities));
            }

        }
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
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.character_details);
        }
    }
}
