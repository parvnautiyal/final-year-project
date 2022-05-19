package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    PieChart pieChartCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle(AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
       // tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        //tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        pieChartCountry = findViewById(R.id.piechartCountry);
        tvCountry.setText(AffectedCountries.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountries.countryModelsList.get(positionCountry).getRecovered());
        tvCritical.setText(AffectedCountries.countryModelsList.get(positionCountry).getCritical());
        tvActive.setText(AffectedCountries.countryModelsList.get(positionCountry).getActive());
        //tvTodayCases.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getDeaths());
        //tvTodayDeaths.setText(AffectedCountries.countryModelsList.get(positionCountry).getTodayDeaths());

        int a = Integer.parseInt(tvCases.getText().toString());
        int b = Integer.parseInt(tvRecovered.getText().toString());
        int c = Integer.parseInt(tvTotalDeaths.getText().toString());
        int d = Integer.parseInt(tvActive.getText().toString());

        pieChartCountry.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
        pieChartCountry.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
        pieChartCountry.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
        pieChartCountry.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#b794f6")));
        if(a == 0 && b == 0 && c == 0 && d == 0)
            pieChartCountry.addPieSlice(new PieModel("Active",0, Color.parseColor("#065535")));

        pieChartCountry.startAnimation();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
