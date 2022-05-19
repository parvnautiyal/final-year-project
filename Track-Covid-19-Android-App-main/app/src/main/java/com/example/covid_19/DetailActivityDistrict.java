package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivityDistrict extends AppCompatActivity {

    private int positionDistrict;
    PieChart pieChartDistrict;
    TextView districtNameDetail , totalCasesdistrict , activedistrictDetail , totalDeathsdistrictDetail , recovereddistrictDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_district);

        Intent intent = getIntent();
        positionDistrict = intent.getIntExtra("posDistrict",0);
        //int add = (DistrictActivity.sizeArrstatic) - (DistrictActivity.sizeArr);
       // positionDistrict = positionDistrict+add;
        getSupportActionBar().setTitle(DistrictActivity.districtModelListForSearch.get(positionDistrict).getDistrictName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pieChartDistrict = findViewById(R.id.piechartDistrict);
        districtNameDetail = findViewById(R.id.districtNamedetail);
        totalCasesdistrict = findViewById(R.id.districtcasesdetail);
        activedistrictDetail = findViewById(R.id.districtActivedetail);
        totalDeathsdistrictDetail = findViewById(R.id.districtDeathsdetail);
        recovereddistrictDetail = findViewById(R.id.districtRecovereddetail);

        districtNameDetail.setText(DistrictActivity.districtModelListForSearch.get(positionDistrict).getDistrictName());
        totalCasesdistrict.setText(DistrictActivity.districtModelListForSearch.get(positionDistrict).getDistrictTotalCases());
        activedistrictDetail.setText(DistrictActivity.districtModelListForSearch.get(positionDistrict).getDistrictActive());
        totalDeathsdistrictDetail.setText(DistrictActivity.districtModelListForSearch.get(positionDistrict).getDistrictTotalDeaths());
        recovereddistrictDetail.setText(DistrictActivity.districtModelListForSearch.get(positionDistrict).getDistrictRecovered());
        int a = Integer.parseInt(totalCasesdistrict.getText().toString());
        int b = Integer.parseInt(recovereddistrictDetail.getText().toString());
        int c = Integer.parseInt(totalDeathsdistrictDetail.getText().toString());
        int d = Integer.parseInt(activedistrictDetail.getText().toString());
        pieChartDistrict.addPieSlice(new PieModel("Cases",Integer.parseInt(totalCasesdistrict.getText().toString()), Color.parseColor("#FFA726")));
        pieChartDistrict.addPieSlice(new PieModel("Recovered",Integer.parseInt(recovereddistrictDetail.getText().toString()), Color.parseColor("#66BB6A")));
        pieChartDistrict.addPieSlice(new PieModel("Deaths",Integer.parseInt(totalDeathsdistrictDetail.getText().toString()), Color.parseColor("#EF5350")));
        pieChartDistrict.addPieSlice(new PieModel("Active",Integer.parseInt(activedistrictDetail.getText().toString()), Color.parseColor("#b794f6")));
        if(a == 0 && b == 0 && c == 0 && d == 0)
        pieChartDistrict.addPieSlice(new PieModel("Active",0/*Integer.parseInt(activedistrictDetail.getText().toString())*/, Color.parseColor("#065535")));

        pieChartDistrict.startAnimation();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
