package com.example.tabbedactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;


public class AboutActivity extends AppCompatActivity {

    public static final String myPreference = "mypref";
    public static final String themeKey = "themeKey";
    SharedPreferences sharedPreferences;
//    Button contribute, bugReport, feedback;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Handles shared preferences
        sharedPreferences = getSharedPreferences(myPreference,
                Context.MODE_PRIVATE);
        if (sharedPreferences.contains(themeKey)) {
            if (sharedPreferences.getString(themeKey, "").equals("1")) {
                setTheme(R.style.DarkTheme);
            }
//            else setTheme(R.style.AppTheme);
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(themeKey, "0");
            editor.apply();
        }
        //Sets the main activity layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);

        // setting app version
        version = findViewById(R.id.app_version);
        String version_number = getApplicationContext()
                .getString(R.string.app_version, appVersion(AboutActivity.this));
        version.setText(version_number);
    }

    public void send_feedback(View view) {
        //Add extras and launch intent to send email
        Intent feedbackEmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "xwaxes@gmail.com", null))//Set recipient and open primary mail client
                .putExtra(Intent.EXTRA_SUBJECT, "Feedback")//Set email subject
                .putExtra(Intent.EXTRA_TEXT, "This is my feedback on the app:" +
                        "\n");//Set email body
        startActivity(Intent.createChooser(feedbackEmailIntent, null));
    }

    public void contribute(View view) {
        //Get system date for bug report
        Date bugReportDate = Calendar.getInstance().getTime();

        //Add extras and launch intent to send email
        Intent bugReportEmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "xwaxes@gmail.com", null))//Set recipient and open primary mail client
                .putExtra(Intent.EXTRA_SUBJECT, "Bug Report")//Set email subject
                .putExtra(Intent.EXTRA_TEXT, "While using your app on " + bugReportDate + ", I discovered this bug:" +
                        "\n");//Set email body
        startActivity(Intent.createChooser(bugReportEmailIntent, null));
    }
    /*
     * Takes Activity Context and returns a String of the App Version e.g 1.0
     */
    static String appVersion(Context context) {
        String result = "";
        try {
            result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .versionName;
            result = result.replaceAll("[a-zA-Z]|-", "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;

    }

}