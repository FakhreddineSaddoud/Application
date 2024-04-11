package com.example.formation;

import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout ;
    private NavigationView navigationView;
    private ImageView iconMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        drawerLayout = findViewById(R.id.drawer_layout_home);
        navigationView= findViewById(R.id.navigation_view_home);
        iconMenu= findViewById(R.id.iconMenuHome);

        navigationTheme();
        navigationView.setNavigationItemSelectedListener(item -> {
                switch (item.getItemId()){
                    case R.id.devices:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.ticketElectrique:
                        //to Do
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.profile:
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        break;
                }
            return true;
        });


    }

    private void navigationTheme() {
        navigationView.setCheckedItem(R.id.devices);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        iconMenu.setOnClickListener(v->{
            if (drawerLayout.isDrawerVisible(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else
                drawerLayout.openDrawer(GravityCompat.START);
        });

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorApp));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}

