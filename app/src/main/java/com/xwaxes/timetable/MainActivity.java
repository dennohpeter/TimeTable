package com.xwaxes.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.xwaxes.timetable.classes.ClassFragment;
import com.google.android.material.navigation.NavigationView;
import com.michaelflisar.changelog.ChangelogBuilder;
import com.michaelflisar.changelog.classes.DefaultAutoVersionNameFormatter;
import com.michaelflisar.changelog.classes.ImportanceChangelogSorter;
import com.xwaxes.timetable.exams.ExamsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private  DrawerLayout drawer;
    private Fragment fragment;
    private SwitchCompat switchNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        switchNightMode = findViewById(R.id.switchNightMode);

        int currentMode = AppCompatDelegate.getDefaultNightMode();

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES ){
            switchNightMode.setChecked(true);
        }else{
            switchNightMode.setChecked(false);
        }

        switchNightMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            restartCurrentActivity();
        });

        drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (savedInstanceState == null){
            fragment = new ClassFragment();
            setTitle(R.string.classes);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            navigationView.setCheckedItem(R.id.nav_classes);
        }
        showChangelog();

    }
    public void restartCurrentActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    private void showChangelog() {
        ChangelogBuilder builder = new ChangelogBuilder()
                .withUseBulletList(true)
                .withManagedShowOnStart(true)
                .withTitle(getString(R.string.app_name) +  " Changelog")
                .withSorter(new ImportanceChangelogSorter())
                .withVersionNameFormatter(new DefaultAutoVersionNameFormatter(DefaultAutoVersionNameFormatter.Type.MajorMinor, "b"));
        builder.buildAndShowDialog(this, false);


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_classes:
                fragment =  new ClassFragment();
                setTitle(R.string.classes);
                break;
            case R.id.nav_exams:
                fragment =  new ExamsFragment();
                setTitle(R.string.exams);
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openSettingsActivity(View view){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void setNightTheme(View view){
        switchNightMode.toggle();
    }

}