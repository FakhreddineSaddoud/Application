package com.example.formation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private EditText fullName, email, cin, phone;
    private Button btnEdit, btnLogOut;
    private DrawerLayout drawerLayout ;
    private NavigationView navigationView;
    private ImageView iconMenu;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();


        fullName = findViewById(R.id.fullNameProfile);
        email    = findViewById(R.id.emailProfile);
        cin      = findViewById(R.id.cinProfile);
        phone    = findViewById(R.id.phoneProfile);
        btnEdit = findViewById(R.id.btnEditProfile);
        btnLogOut= findViewById(R.id.btnSignOut);
        drawerLayout = findViewById(R.id.drawer_layout_profile);
        navigationView= findViewById(R.id.navigation_view_profile);
        iconMenu= findViewById(R.id.iconMenuProfile);


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        databaseReference  = firebaseDatabase.getReference().child("Users").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fullName.setText(snapshot.child("fullName").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
                cin.setText(snapshot.child("cin").getValue().toString());
                phone.setText(snapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "error !!", Toast.LENGTH_SHORT).show();
            }
        });




        btnLogOut.setOnClickListener(view ->{
             firebaseAuth.signOut();
            SharedPreferences preferences = getSharedPreferences("checkBox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("rememberMe", false);
            editor.apply();
            startActivity(new Intent(ProfileActivity.this,SignInActivity.class ));

        });

        btnEdit.setOnClickListener(v ->{

            btnEdit.setText("save");
            fullName.setFocusableInTouchMode(true);
            cin.setFocusableInTouchMode(true);
            phone.setFocusableInTouchMode(true);

                btnEdit.setOnClickListener(v1->{
                    String updateFullName = fullName.getText().toString().trim();
                    String updateCIN = cin.getText().toString().trim();
                    String updatePhone = phone.getText().toString().trim();

                    databaseReference.child("fullName").setValue(updateFullName);
                    databaseReference.child("cin").setValue(updateCIN);
                    databaseReference.child("phone").setValue(updatePhone);
                    Toast.makeText(this, "Your data has been changed successfully ! ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                });
        });

        navigationTheme();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.devices:
                    startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                    break;
                case R.id.ticketElectrique:
                    //to Do
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.profile:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });




    }


    private void navigationTheme() {
        navigationView.setCheckedItem(R.id.profile);
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