package com.iet.taskplanner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.iet.taskplanner.model.User;
import com.iet.taskplanner.services.AuthService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private AuthService authService;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:/10.0.2.2:8080") //localhost for emulator
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        authService = retrofit.create(AuthService.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
    }

    public void onLogin(View view) {
        EditText emailText = findViewById(R.id.idActivity_LoginEmail);
        EditText passwordText = findViewById(R.id.idActivity_LoginPassword);
        User user = new User(emailText.getText().toString(), passwordText.getText().toString());
        executorService.execute(() -> {
            login(user);
        });

    }

    private void login(User user) {
        try {
            String token = authService.getToken(user).execute().body().getAccessToken();
            SharedPreferences sharedPref =
                    getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE );
            SharedPreferences.Editor edit = sharedPref.edit();
            edit.putString("TOKEN_KEY", token);
            edit.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
