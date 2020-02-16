package edu.uci.tindor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StartupActivity extends AppCompatActivity {

    public static String TAG = "StartupActivity";
    String url = "http://35.236.111.125/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                StartupActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView myTextView = (TextView) findViewById(R.id.debugNetTextView);
                        myTextView.setText("Connection failed. Please start the server!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    StartupActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView myTextView = (TextView) findViewById(R.id.debugNetTextView);
                            myTextView.setText("Connection failed. Please start the server!");
                        }
                    });
                }
                // Read data on the worker thread
                final String responseData = response.body().string();

                // Run view-related code back on the main thread
                StartupActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView myTextView = (TextView) findViewById(R.id.debugNetTextView);
                        myTextView.setText(responseData);
                    }
                });
            }
        });
    }

    public void onLoginClicked(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onSignupClicked(View view){
        startActivity(new Intent(this, SignupActivity.class));
    }
}
