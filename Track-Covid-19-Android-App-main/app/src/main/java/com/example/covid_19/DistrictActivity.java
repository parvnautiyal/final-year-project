package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DistrictActivity extends AppCompatActivity {

    public int n , i;
    public static int sizeArr , sizeArrstatic,sizeprev=0,m=0;
    public String stateNameFor;
    ListView listViewDistrict;
    EditText searchDis;
    SimpleArcLoader simpleArcLoaderDistrict;
    public List<DistrictModel> districtModelListm = new ArrayList<>();
    public static List<DistrictModel> districtModelListForDetail = new ArrayList<>();
    public static List<DistrictModel> districtModelListForSearch = new ArrayList<>();
    DistrictModel districtModel;
    MyDistrictCustomAdapter myDistrictCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        listViewDistrict = findViewById(R.id.listViewDistrict);
        simpleArcLoaderDistrict = findViewById(R.id.loaderDistrict);
        searchDis = findViewById(R.id.edtSearchDistrict);
        getSupportActionBar().setTitle("Districts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchDataDistrict();

        listViewDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailActivityDistrict.class).putExtra("posDistrict",position));
            }
        });

        searchDis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myDistrictCustomAdapter.getFilter().filter(s);
                myDistrictCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    private void fetchDataDistrict() {

     stateNameFor = DetailActivityState.stateName;
     if(stateNameFor.equals("Maharashtra"))
            i=20;
     if(stateNameFor.equals("Tamil Nadu"))
            i=32;
     if(stateNameFor.equals("Gujarat"))
            i=11;
        if(stateNameFor.equals("Delhi"))
            i=8;
        if(stateNameFor.equals("Rajasthan"))
            i=29;
        if(stateNameFor.equals("Madhya Pradesh"))
            i=23;
        if(stateNameFor.equals("Uttar Pradesh"))
            i=34;
        if(stateNameFor.equals("West Bengal"))
            i=36;
        if(stateNameFor.equals("Andhra Pradesh"))
            i=2;
        if(stateNameFor.equals("Punjab"))
            i=27;
        if(stateNameFor.equals("Bihar"))
            i=5;
        if(stateNameFor.equals("Telangana"))
            i=31;
        if(stateNameFor.equals("Karnataka"))
            i=16;
        if(stateNameFor.equals("Jammu and Kashmir"))
            i=15;
        if(stateNameFor.equals("Odisha"))
            i=26;
        if(stateNameFor.equals("Haryana"))
            i=13;
        if(stateNameFor.equals("Kerala"))
            i=17;
        if(stateNameFor.equals("Jharkhand"))
            i=14;
        if(stateNameFor.equals("Chandigarh"))
            i=6;
        if(stateNameFor.equals("Assam"))
            i=4;
        if(stateNameFor.equals("Tripura"))
            i=33;
        if(stateNameFor.equals("Uttarakhand"))
            i=35;
        if(stateNameFor.equals("Himachal Pradesh"))
            i=12;
        if(stateNameFor.equals("Chhattisgarh"))
            i=7;
        if(stateNameFor.equals("Goa"))
            i=10;
        if(stateNameFor.equals("Ladakh"))
            i=18;
        if(stateNameFor.equals("Andaman and Nicobar Islands"))
            i=1;
        if(stateNameFor.equals("Manipur"))
            i=22;
        if(stateNameFor.equals("Puducherry"))
            i=28;
        if(stateNameFor.equals("Meghalaya"))
            i=21;
        if(stateNameFor.equals("Mizoram"))
            i=24;
        if(stateNameFor.equals("Arunachal Pradesh"))
            i=3;
        if(stateNameFor.equals("Dadra and Nagar Haveli and Daman and Diu"))
            i=9;
        if(stateNameFor.equals("Nagaland"))
            i=25;
        if(stateNameFor.equals("Lakshadweep"))
            i=19;
        if(stateNameFor.equals("Sikkim"))
            i=30;


        Log.v("district","Value of i="+i);
        Log.v("district","Value of n Position="+n);
        Log.v("district","Value of State="+stateNameFor);

        String url = "https://api.covid19india.org/v2/state_district_wise.json";

        simpleArcLoaderDistrict.start();
        StringRequest reqDistrict = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArrayRoot = new JSONArray(response);
                    JSONObject jsonObjectDistrict = jsonArrayRoot.getJSONObject(i);
                    JSONArray districtArray = jsonObjectDistrict.getJSONArray("districtData");
                    int j=0;
                    for(j=0;j<districtArray.length();j++)
                    {
                        JSONObject disritctObject = districtArray.getJSONObject(j);

                        String districtName = disritctObject.getString("district");
                        String districtTotalCases = disritctObject.getString("confirmed");
                        String districtActive = disritctObject.getString("active");
                        String districtDeaths = disritctObject.getString("deceased");
                        String districtRecovered = disritctObject.getString("recovered");

                        Log.d("district","Value of "+districtName);

                        districtModel = new DistrictModel(districtName,districtTotalCases,districtActive,districtDeaths,districtRecovered);
                        districtModelListm.add(districtModel);
                        districtModelListForDetail.add(districtModel);
                    }
                        districtModelListForSearch.clear();
                        for (int i=0;i<districtModelListm.size();i++)
                            districtModelListForSearch.add(districtModelListm.get(i));
                        sizeArr = districtArray.length();
                        m = m+sizeArr;
                        sizeArrstatic = districtModelListForDetail.size();
                    Log.d("district","Value of District Array "+sizeArr);
                    Log.d("district","Value of ArrayList Size "+districtModelListm.size());
                    Log.d("district","Value of M "+m);
                    Log.d("district","Value of ArrayList Size Static "+districtModelListForDetail.size());
                    myDistrictCustomAdapter = new MyDistrictCustomAdapter(DistrictActivity.this,districtModelListm);
                    listViewDistrict.setAdapter(myDistrictCustomAdapter);
                    simpleArcLoaderDistrict.stop();
                    simpleArcLoaderDistrict.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoaderDistrict.stop();
                    simpleArcLoaderDistrict.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoaderDistrict.stop();
                simpleArcLoaderDistrict.setVisibility(View.GONE);
                Toast.makeText(DistrictActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueueDistrict = Volley.newRequestQueue(this);
        requestQueueDistrict.add(reqDistrict);
    }
}
