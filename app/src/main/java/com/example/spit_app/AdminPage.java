package com.example.spit_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        BottomNavigationView navView2 = findViewById(R.id.nav_view2);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration2 = new AppBarConfiguration.Builder(
                R.id.navigation_announcements2, R.id.navigation_home2, R.id.navigation_events2)
                .build();
        NavController navController2 = Navigation.findNavController(this, R.id.nav_host_fragment2);
        NavigationUI.setupActionBarWithNavController(this, navController2, appBarConfiguration2);
        NavigationUI.setupWithNavController(navView2, navController2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings2:
                return true;

            case R.id.action_change_username2:
                startActivity(new Intent(AdminPage.this, Change_username.class));
                return true;

            case R.id.action_change_password2:
                startActivity(new Intent(AdminPage.this, Change_password.class));
                return true;

            case R.id.action_profile2:
                startActivity(new Intent(AdminPage.this, Profile.class));
                return true;

            case R.id.action_help2:
                startActivity(new Intent(AdminPage.this, Help.class));
                return true;

            case R.id.action_about2:
                startActivity(new Intent(AdminPage.this, About.class));
                return true;

            case R.id.action_logout2:

                Intent logout=new Intent(AdminPage.this, login_form.class);
                startActivity(logout);
                finish();
                Toast.makeText(AdminPage.this, "You have logged out successfully!", Toast.LENGTH_LONG).show();
                return true;

            default:return super.onOptionsItemSelected(item);
        }

    }
}
