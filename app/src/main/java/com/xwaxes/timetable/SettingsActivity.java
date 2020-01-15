package com.xwaxes.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.michaelflisar.changelog.ChangelogBuilder;
import com.michaelflisar.changelog.classes.DefaultAutoVersionNameFormatter;
import com.michaelflisar.changelog.classes.ImportanceChangelogSorter;

public class SettingsActivity  extends AppCompatActivity {
    private SwitchCompat switchNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchNightMode = findViewById(R.id.switchNightMode);

        int currentMode = AppCompatDelegate.getDefaultNightMode();

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            switchNightMode.setChecked(true);
        } else {
            switchNightMode.setChecked(false);
        }

        switchNightMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            restartCurrentActivity();
        });
    }

    public void setNightTheme(View view) {
        switchNightMode.toggle();
    }

    public void restartCurrentActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void showChangelog(View view) {
        ChangelogBuilder builder = new ChangelogBuilder()
                .withUseBulletList(true)
                .withManagedShowOnStart(false)
                .withTitle(getString(R.string.app_name) +  " Changelog")
                .withSorter(new ImportanceChangelogSorter())
                .withVersionNameFormatter(new DefaultAutoVersionNameFormatter(DefaultAutoVersionNameFormatter.Type.MajorMinor, "b"));
        builder.buildAndShowDialog(this, false);

    }
}
