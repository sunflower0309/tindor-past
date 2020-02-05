package edu.uci.tindor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    public void onLoginClicked(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void onSignupClicked(View view){
        startActivity(new Intent(this, SignupActivity.class));
    }
}
