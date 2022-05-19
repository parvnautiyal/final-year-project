package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


public class DetailActivityState extends AppCompatActivity {

    private int positionState;
    public static String stateName;
    PieChart pieChartState;
    TextView stateNameDetail , totalCasesState , activeStateDetail , totalDeathsDetail , recoveredStateDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_state);

        Intent intent = getIntent();
        positionState = intent.getIntExtra("pos",0);
        Log.v("detailActivityState","Value of "+positionState);

        Log.v("State Activity","Value of positionState "+positionState);

        getSupportActionBar().setTitle(StateActivity.stateModelList.get(positionState).getState());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pieChartState = findViewById(R.id.piechartState);
        stateNameDetail = findViewById(R.id.statenamedetail);
        totalCasesState = findViewById(R.id.totalCasesState);
        activeStateDetail = findViewById(R.id.activeCasesState);
        totalDeathsDetail = findViewById(R.id.totalDeathsstate);
        recoveredStateDetail = findViewById(R.id.recoveredState);
        stateName = StateActivity.stateModelList.get(positionState).getState();
        if (stateName.equals("Andaman and Nicobar Islands"))
        {
            stateNameDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.textsize));
        }
        if (stateName.equals("Dadra and Nagar Haveli and Daman and Diu"))
        {
            stateNameDetail.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.textsizeDaman));
        }
        stateNameDetail.setText(StateActivity.stateModelList.get(positionState).getState());
        totalCasesState.setText(StateActivity.stateModelList.get(positionState).getTotalcases());
        activeStateDetail.setText(StateActivity.stateModelList.get(positionState).getActive());
        totalDeathsDetail.setText(StateActivity.stateModelList.get(positionState).getTotaldeaths());
        recoveredStateDetail.setText(StateActivity.stateModelList.get(positionState).getRecovered());


        int a = Integer.parseInt(totalCasesState.getText().toString());
        int b = Integer.parseInt(recoveredStateDetail.getText().toString());
        int c = Integer.parseInt(totalDeathsDetail.getText().toString());
        int d = Integer.parseInt(activeStateDetail.getText().toString());
        pieChartState.addPieSlice(new PieModel("Cases",Integer.parseInt(totalCasesState.getText().toString()), Color.parseColor("#FFA726")));
        pieChartState.addPieSlice(new PieModel("Recovered",Integer.parseInt(recoveredStateDetail.getText().toString()), Color.parseColor("#66BB6A")));
        pieChartState.addPieSlice(new PieModel("Deaths",Integer.parseInt(totalDeathsDetail.getText().toString()), Color.parseColor("#EF5350")));
        pieChartState.addPieSlice(new PieModel("Active",Integer.parseInt(activeStateDetail.getText().toString()), Color.parseColor("#b794f6")));
        if(a == 0 && b == 0 && c == 0 && d == 0)
            pieChartState.addPieSlice(new PieModel("Active",0, Color.parseColor("#065535")));

        pieChartState.startAnimation();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public void trackDistrict(View view) {

        Intent i = new Intent(DetailActivityState.this,DistrictActivity.class);
        startActivity(i);
    }
}
