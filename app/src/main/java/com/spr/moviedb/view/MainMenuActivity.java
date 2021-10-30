package com.spr.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.spr.moviedb.R;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNV;
    NavHostFragment navHF;
    Toolbar toolbar_mainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        bottomNV = findViewById(R.id.bottomnav_mainmenu);
        toolbar_mainmenu = findViewById(R.id.toolbar_mainmenu);
        setSupportActionBar(toolbar_mainmenu);
        navHF = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navfrag_mainmenu);
        AppBarConfiguration appBC = new AppBarConfiguration.Builder(R.id.nowPlayingFragment,R.id.upComingFragment,R.id.PopularFragment,R.id.searchFragment).build();
        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this, navHF.getNavController(),appBC);
        NavigationUI.setupWithNavController(bottomNV, navHF.getNavController());
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navHF.getNavController().navigateUp() || super.onSupportNavigateUp();
    }
}