package com.jg.gradingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jg.gradingapp.fragment.GradeEntryFragment;
import com.jg.gradingapp.fragment.GradeSearchFragment;
import com.jg.gradingapp.fragment.GradeViewFragment;

public class MainActivity extends AppCompatActivity {
private Toolbar toolbar;
private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Navigation drawer menu button click function
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //It will navigate to the specific fragment
                switch (menuItem.getItemId()){
                    case R.id.nav_grade_entry:
                        //Move to Grade entry fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GradeEntryFragment()).commit();
                        break;
                    case R.id.nav_grade_view:
                        //Move to all grade view fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GradeViewFragment()).commit();
                        break;
                    case R.id.nav_grade_search:
                        //move to search fragment
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GradeSearchFragment()).commit();
                        break;

                }
                //To Close drawer after taping menu item
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
//Navigation drawer button on the top left side; It will open Nav drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Initially showing first fragment Greade Entry Fragment; cheacking: if app load firsttime;
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new GradeEntryFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_grade_entry);
        }


    }
//if you tap outside navigation drawer it will close the drawer.
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }
}