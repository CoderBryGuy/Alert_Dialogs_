package com.example.alert_dialogs_;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView myTextView;
    final static String LNG_BOOL = "language bool";
    String myLangPrefIntro = "language preference = ";
    String myLangPref = "";
    boolean langPrefSet = false;
    final static String ENGLISH = "English";
    final static String SPANISH = "Spanish";
    final static String LANG_KEY = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (TextView) findViewById(R.id.myTextView);
        sharedPreferences = this.getSharedPreferences("com.example.memorable_places_app", Context.MODE_PRIVATE);
        langPrefSet = sharedPreferences.getBoolean(LNG_BOOL, false);
        updatePreferences();
        callDialogBuilder();
    }

    private void callDialogBuilder() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Language Preferences")
                .setMessage("Choose Which Language To Use?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "English!", Toast.LENGTH_SHORT).show();
                        sharedPreferences.edit().putString(LANG_KEY, "english").apply();
                        sharedPreferences.edit().putBoolean(LNG_BOOL, true);
                        updatePreferences();
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Spanish!", Toast.LENGTH_SHORT).show();
                        sharedPreferences.edit().putString(LANG_KEY, "spanish").apply();
                        sharedPreferences.edit().putBoolean(LNG_BOOL, true);
                        updatePreferences();

                    }
                })
                .show();
    }

    private void updatePreferences() {
        myLangPref = myLangPrefIntro;

        if(!langPrefSet) {
            myLangPref += sharedPreferences.getString(LANG_KEY, myLangPrefIntro + "no set language preference.");
            myTextView.setText(myLangPref);
            langPrefSet = true;
        }else{
            myLangPref += sharedPreferences.getString(LANG_KEY, "no set language preference found");
            myTextView.setText(myLangPref);
        }

    }
}