package com.example.timetable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AboutActivity extends AppCompatActivity {

    public static final String myPreference = "mypref";
    public static final String themeKey = "themeKey";
    SharedPreferences sharedPreferences;
//    Button contribute, bugReport, feedback;
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);
        // setting back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

    public void showCredit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.credits);

        View popupLayout = getLayoutInflater().inflate(R.layout.contributor_list_view, null);
        HashMap<String, String> contributor_roles = new HashMap<>();
        contributor_roles.put(getString(R.string.creator), getString(R.string.creator_roles));
        contributor_roles.put(getString(R.string.contributor1), getString(R.string.developer1_roles));
        contributor_roles.put(getString(R.string.contributor2), getString(R.string.developer2_roles));

        List<HashMap<String, String>> listItem = new ArrayList<>();

        ListView listView = popupLayout.findViewById(R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(this,listItem,R.layout.contributor_list_item,
                new String[]{"name", "role"},
                new int[]{R.id.contributor_name, R.id.developer_role});
        for (Map.Entry<String, String> pair : contributor_roles.entrySet()) {
            HashMap<String, String> resultMap = new HashMap<>();
            resultMap.put("name", ((Map.Entry) pair).getKey().toString());
            resultMap.put("role", ((Map.Entry) pair).getValue().toString());
            listItem.add(resultMap);
        }
        listView.setAdapter(adapter);

        builder.setView(popupLayout);
        builder.setNegativeButton(R.string.ok, (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    public void rateApp(View view) {
        Toast.makeText(this, "Rate this app", Toast.LENGTH_SHORT).show();
    }
}