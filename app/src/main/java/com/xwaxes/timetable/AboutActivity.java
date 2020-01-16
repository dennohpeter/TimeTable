package com.xwaxes.timetable;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;
import java.util.Map;


public class AboutActivity extends AppCompatActivity {

    private String version_number;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);
        // setting back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // setting app version_textView
        TextView version_textView = findViewById(R.id.app_version);
        version_number = getApplicationContext()
                .getString(R.string.app_version, appVersion(AboutActivity.this));
        version_textView.setText(version_number);
    }

    public void send_feedback(View view) {
        String subject = "Feedback For "
                + getString(R.string.app_name)
                + " " + version_number;
        //Add extras and launch intent to send email
        Intent feedbackEmailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "xwaxes@gmail.com", null))
                .putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(Intent.createChooser(feedbackEmailIntent, subject));
    }

    public void contribute(View view) {
        //Add extras and launch intent to send email
        Intent contribute = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "xwaxes@gmail.com", null))
                .putExtra(Intent.EXTRA_SUBJECT, "Contribution")
                .putExtra(Intent.EXTRA_TEXT,
                        "Please contact us if you want to help improve this app:) \n" +
                                "As a small reward, we will add your name to list of contributors.");//Set email body
        startActivity(Intent.createChooser(contribute, "Contribution"));
    }

    public void showCredit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.credits);

        View popupLayout = getLayoutInflater().inflate(R.layout.contributor_list_view, null);
        HashMap<String, String> contributor_roles = new HashMap<>();
        contributor_roles.put(getString(R.string.creator), getString(R.string.creator_roles));
        contributor_roles.put(getString(R.string.contributor1), getString(R.string.developer1_roles));
        contributor_roles.put(getString(R.string.contributor2), getString(R.string.developer2_roles));
        contributor_roles.put(getString(R.string.contributor3), getString(R.string.developer3_roles));

        List<HashMap<String, String>> listItem = new ArrayList<>();

        ListView listView = popupLayout.findViewById(R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(this, listItem, R.layout.contributor_list_item,
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