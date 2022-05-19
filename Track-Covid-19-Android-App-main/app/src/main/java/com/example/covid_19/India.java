package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class India extends AppCompatActivity {

    TextView textViewCases , textViewActive,textViewDeaths,textViewRecovered;
    SimpleArcLoader simpleArc;
    LinearLayout linearLayout;
    PieChart pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india);

        getSupportActionBar().setTitle("India");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewCases = findViewById(R.id.totalCases);
        textViewActive = findViewById(R.id.activeCases);
        textViewDeaths = findViewById(R.id.totalDeaths);
        linearLayout  = findViewById(R.id.layout_india);
        textViewRecovered = findViewById(R.id.totalRecovered);

        simpleArc = findViewById(R.id.loader1);
        pie = findViewById(R.id.piechart1);

        fetchIndiaData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    private void fetchIndiaData() {

     String url = "https://api.covid19india.org/data.json";
     simpleArc.start();

        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response.toString());
                            JSONArray jsonarr = jsonObj.getJSONArray("statewise");
                            JSONObject obj = jsonarr.getJSONObject(0);

                            textViewCases.setText(obj.getString("confirmed"));
                            textViewActive.setText(obj.getString("active"));
                            textViewDeaths.setText(obj.getString("deaths"));
                            textViewRecovered.setText(obj.getString("recovered"));

                            pie.addPieSlice(new PieModel("Total Cases", Integer.parseInt(textViewCases.getText().toString()), Color.parseColor("#FFA726")));
                            pie.addPieSlice(new PieModel("Recovered", Integer.parseInt(textViewRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pie.addPieSlice(new PieModel("Total Deaths", Integer.parseInt(textViewDeaths.getText().toString()), Color.parseColor("#EF5350")));
                            pie.addPieSlice(new PieModel("Active", Integer.parseInt(textViewActive.getText().toString()), Color.parseColor("#29B6F6")));
                            pie.startAnimation();
                            simpleArc.stop();
                            simpleArc.setVisibility(View.GONE);
                          //  linearLayout.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArc.stop();
                            simpleArc.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArc.stop();
                simpleArc.setVisibility(View.GONE);

                Toast.makeText(India.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requesqueue = Volley.newRequestQueue(this);
        requesqueue.add(req);
    }

    public void trackState(View view) {
        Intent i = new Intent(India.this,StateActivity.class);
        startActivity(i);
    }
}
