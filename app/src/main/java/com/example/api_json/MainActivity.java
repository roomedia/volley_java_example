package com.example.api_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    String url = "https://jsonplaceholder.typicode.com/todos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                StringBuilder text = new StringBuilder("response:");
                for (int i = 0; i < response.length(); i++) {
                    text.append("\n").append(response.getJSONObject(i).getString("title"));
                }
                textView.setText(text.toString());
            } catch (Exception error) {
                textView.setText("error: " + error.getMessage());
            }
        }, error -> {
            textView.setText("error: " + error.getMessage());
        });

        queue.add(jsonArrayRequest);
    }
}