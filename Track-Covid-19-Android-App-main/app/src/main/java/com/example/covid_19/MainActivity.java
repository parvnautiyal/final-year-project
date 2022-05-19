package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    RecyclerView recyclerView,recyclerViewPrecations;
    LinearLayout linearLayoutmain;
    ArrayList<MainModel> mainModels,mainModelsPrecuations;
    MainAdapter mainAdapter,mainAdapterPrecautions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedContries);

        simpleArcLoader = findViewById(R.id.loader);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutmain = findViewById(R.id.linearmain);
        recyclerViewPrecations = findViewById(R.id.recyclerViewPrecautions);
        pieChart = findViewById(R.id.piechart);

        fetchData();

       Integer[] logo = {R.drawable.cough,R.drawable.fever,R.drawable.tiredness_new,R.drawable.muscleache,R.drawable.headache,R.drawable.chestpain};
        String[] symptom = {"Cough","Fever","Tiredness","Muscle Ache","Headache","Chest Pain"};

        Integer[] prec_logo = {R.drawable.stayhome,R.drawable.use_mask,R.drawable.social_distance,R.drawable.wash_hands,R.drawable.mass_gathering,R.drawable.travel};
        String[] precautions = {"Stay Home","Use Mask","Social distancing","Wash hands","Avoid mass gathering","Restrict travel"};

        mainModels = new ArrayList<MainModel>();
        mainModelsPrecuations= new ArrayList<MainModel>();
        for (int i=0;i<logo.length;i++)
        {
            MainModel model = new MainModel(logo[i],symptom[i]);
            mainModels.add(model);
        }
        for (int i=0;i<prec_logo.length;i++)
        {
            MainModel modelp = new MainModel(prec_logo[i],precautions[i]);
            mainModelsPrecuations.add(modelp);
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );
    recyclerView.setLayoutManager(linearLayoutManager2);
    recyclerView.setItemAnimator(new DefaultItemAnimator());

     mainAdapter = new MainAdapter(MainActivity.this,mainModels);
     recyclerView.setAdapter(mainAdapter);

     recyclerViewPrecations.setLayoutManager(linearLayoutManager);
     recyclerViewPrecations.setItemAnimator(new DefaultItemAnimator());

     mainAdapterPrecautions = new MainAdapter(MainActivity.this,mainModelsPrecuations);
     recyclerViewPrecations.setAdapter(mainAdapterPrecautions);


       }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#b794f6")));
                            pieChart.startAnimation();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            linearLayoutmain.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"No Internet",Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTrackCountries(View view) {
        Intent i = new Intent(MainActivity.this, AffectedCountries.class);
        startActivity(i);
    }

    public void goTrackIndia(View view) {
        Intent i = new Intent(MainActivity.this, India.class);
        startActivity(i);
    }

    public void knowMore(View view) {
        Intent i = new Intent(MainActivity.this, KnowMoreActivity.class);
        startActivity(i);
    }

    public void helpline(View view) {
        Intent i = new Intent(MainActivity.this, HelplineNumber.class);
        startActivity(i);
    }
}
