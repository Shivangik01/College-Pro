package com.example.spit_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserPages extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pages);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_announcements1, R.id.navigation_home1, R.id.navigation_events1)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;

            case R.id.action_change_password:
                startActivity(new Intent(UserPages.this, Change_password.class));
                return true;

            case R.id.action_profile:
                startActivity(new Intent(UserPages.this, Profile.class));
                return true;

            case R.id.action_help:
                startActivity(new Intent(UserPages.this, Help.class));
                return true;

            case R.id.action_about:
                startActivity(new Intent(UserPages.this, About.class));
                return true;

            case R.id.action_logout:

                Intent logout=new Intent(UserPages.this, login_form.class);
                startActivity(logout);
                finish();
                Toast.makeText(UserPages.this, "You have logged out successfully!", Toast.LENGTH_LONG).show();
                return true;

             default:return super.onOptionsItemSelected(item);
        }

    }
}
