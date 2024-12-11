package dam.pmdm.personajesmario;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import dam.pmdm.personajesmario.databinding.ActivityMainBinding;

/**
 * La actividad principal de la aplicación, donde se configura la navegación y las interacciones con el usuario.
 * Se encarga de manejar la barra de herramientas, el menú, los fragmentos y la comunicación con los diferentes componentes de la UI.
 *
 * Aplicación desarrollada por Pedro Javier Morales Leyva.
 * Versión 1.0
 */
public class MainActivity extends AppCompatActivity {

    private NavController navController; // Controlador de navegación para gestionar los fragmentos
    private ActivityMainBinding binding; // Enlace a las vistas de la actividad
    private NavigationView navigationView; // Vista de navegación lateral
    private Switch switchLanguage; // Switch para cambiar el idioma de la aplicación

    private Bundle bundle; // Bundle para pasar datos entre fragmentos

    /**
     * Método llamado al crear la actividad.
     * Configura la barra de herramientas y el controlador de navegación, y muestra un Snackbar de bienvenida.
     * @param savedInstanceState El estado guardado, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el navController
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Configura el Drawer Layout y el NavigationView
        setSupportActionBar(binding.toolbar);
        NavigationView navigationView = binding.navView;

        // Configurar el item click listener para el Drawer
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            return onDrawerItemSelected(menuItem);
        });

        // Configurar el Drawer Toggle para abrir/cerrar el menú lateral
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.open_drawer, R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Busca el Switch desde el ítem del menú
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.switch_language);
        View actionView = menuItem.getActionView(); // Obtén la vista personalizada (switch_item.xml)


        if (actionView != null) {
            Switch switchLanguage = actionView.findViewById(R.id.languageSwitch); // Encuentra el Switch


            if (switchLanguage != null) {
                // Configura el estado inicial del Switch
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

                boolean isEnglish = preferences.getBoolean("language", false); // Obtén el idioma guardado
                switchLanguage.setChecked(isEnglish);

                if (switchLanguage.isChecked()) {
                    setLocale("en"); // Cambiar a inglés
                } else {
                    setLocale("es"); // Cambiar a español
                }

                // Configura el listener para cambios
                switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("language", isChecked);
                    editor.apply();

                    // Cambia el idioma
                    if (isChecked) {
                        setLocale("en"); // Cambiar a inglés
                    } else {
                        setLocale("es"); // Cambiar a español
                    }
                    recreate();
                });
                // Cambia el idioma

            } else {
                Log.e("MainActivity", "Switch not found in actionView");
            }
        } else {
            Log.e("MainActivity", "actionView not found for menu item");
        }


        // Mostrar el Snackbar de bienvenida
        Snackbar.make(findViewById(R.id.mainLayout), R.string.welcome_message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Método para inflar el menú de opciones de la actividad.
     * @param menu El menú en el que inflar los elementos.
     * @return true si el menú se infla correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);  // Inflar el menú desde el archivo XML
        return true;
    }

    /**
     * Método para manejar las selecciones de elementos del menú.
     * @param item El elemento del menú seleccionado.
     * @return true si se maneja la selección del menú.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Verificamos si el clic es en el menú principal (Toolbar)
        if (item.getItemId() == R.id.action_about) {
            // Mostrar el diálogo "Acerca de..."
            showAboutDialog();
            return true;
        }

        // Si no se ha encontrado ninguna coincidencia, llamamos al super método
        return super.onOptionsItemSelected(item);
    }

    /**
     * Método para manejar las selecciones del menú lateral (Drawer).
     * @param item El elemento del menú seleccionado.
     * @return true si se maneja la selección del ítem del menú.
     */
    public boolean onDrawerItemSelected(MenuItem item) {
        // Verificamos si el clic es en el Drawer
        if (item.getItemId() == R.id.nav_home) {
            // Acción cuando se selecciona "Home" en el Drawer
            navController.navigate(R.id.mainFragment);
            binding.drawerLayout.closeDrawer(GravityCompat.START); // Cerrar el Drawer después de seleccionar
            return true;
        }

        return false;
    }

    /**
     * Muestra un cuadro de diálogo "Acerca de..." con la información sobre la aplicación.
     */
    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setTitle(getString(R.string.about));

        // Personalización del diálogo
        builder.setMessage(getString(R.string.about_message))
                .setIcon(R.drawable.about) // Icono de la aplicación
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Cerrar el diálogo
                    }
                });


        // Crear el diálogo
        AlertDialog dialog = builder.create();

        // Mostrar el diálogo
        dialog.show();

        // Ajustar el tamaño del cuadro de diálogo
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85); // 85% del ancho de la pantalla
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;  // Alto ajustado al contenido
        dialog.getWindow().setAttributes(layoutParams);
    }

    /**
     * Método que maneja el clic en un personaje desde el RecyclerView.
     * Pasa los datos del personaje al fragmento de detalle y navega hasta allí.
     * @param character El objeto CharacterData que contiene la información del personaje.
     * @param view La vista desde la que se hizo clic.
     */
    public void characerClicked(CharacterData character, View view) {
        // Crear un Bundle con los datos del personaje
        bundle = new Bundle();
        bundle.putString("name", character.getName()); // Nombre del personaje
        bundle.putInt("image", character.getImage()); // Imagen del personaje
        bundle.putString("description", character.getDescription()); // Descripción del personaje
        bundle.putString("habilities", character.getHabilities()); // Habilidades del personaje
        // Navegar al fragmento de detalles del personaje
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);

        // Mostrar un Toast con el nombre del personaje
        Toast.makeText(this, getString(R.string.toast_text) + " " + character.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Método para cambiar el idioma de la aplicación.
     * Cambia el idioma de la aplicación a inglés o español y guarda la preferencia.
     * @param languageCode El código del idioma (por ejemplo, "en" para inglés, "es" para español).
     */
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Guardar el idioma preferido en SharedPreferences
        SharedPreferences preferences = getSharedPreferences("app_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", languageCode);
        editor.apply();

        // Navegamos a la pantalla anterior para que se refresque
        navController.navigate(R.id.mainFragment);
    }

    /**
     * Permite la navegación hacia arriba (por ejemplo, al presionar la flecha de retroceso en la barra de acción).
     * @return true si la navegación hacia arriba fue exitosa.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
