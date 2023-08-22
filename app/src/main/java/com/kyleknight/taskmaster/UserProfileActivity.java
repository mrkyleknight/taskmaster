package com.kyleknight.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {
    public static final String USER_NICKNAME_TAG = "userNickname";
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setupUserNicknameEditText();
        setupSaveButton();
    }

    void setupUserNicknameEditText() {
        String userNickname = preferences.getString(USER_NICKNAME_TAG, null);
        ((EditText)findViewById(R.id.UserProfileActivityInputText)).setText(userNickname);
    }

    void setupSaveButton() {
        ((Button)findViewById(R.id.UserProfileActivitySaveButton)).setOnClickListener(v -> {
            SharedPreferences.Editor preferencesEditor = preferences.edit();
            EditText userNicknameEditText = (EditText) findViewById(R.id.UserProfileActivityInputText);
            String userNicknameString = userNicknameEditText.getText().toString();

            preferencesEditor.putString(USER_NICKNAME_TAG, userNicknameString);
            preferencesEditor.apply();


            Toast.makeText(UserProfileActivity.this, "Settings saved!", Toast.LENGTH_SHORT).show();
        });
    }
}