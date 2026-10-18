package com.fitness.myapplication6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.fitness.myapplication6.databinding.ActivityMainBinding;

/**
 * Activitatea principală a aplicației care găzduiește componentele de navigare (Navigation Component).
 */
public class MainActivity extends AppCompatActivity {

    // Controlerul responsabil pentru gestionarea navigării între fragmente
    private NavController navController;
    
    // Configurația bării de aplicație (App Bar) asociată cu graficul de navigare
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Configurăm fereastra pentru a permite afișarea conținutului sub barele sistemului (edge-to-edge)
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        super.onCreate(savedInstanceState);

        // Inflăm layout-ul folosind ViewBinding
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setăm Toolbar-ul ca Action Bar-ul activității
        setSupportActionBar(binding.toolbar);

        // Aplicăm insets (margini) pentru bara de stare a sistemului (Status Bar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.appBar, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0);
            return insets;
        });

        // Preluăm NavHostFragment-ul definit în layout și inițializăm NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            // Conectăm Action Bar-ul cu NavController pentru a gestiona titlul și butonul "Back"
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        }
    }

    /**
     * Gestionează acțiunea de navigare în sus (butonul "Back" / sageata de revenire din Toolbar).
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
