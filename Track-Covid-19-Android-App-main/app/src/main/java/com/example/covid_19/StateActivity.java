package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.List;

public class StateActivity extends AppCompatActivity {

    EditText search;
    ListView listViewState;
    SimpleArcLoader simpleArcLoaderState;

    public static List<StateModel> stateModelList = new ArrayList<>();
    StateModel stateModel;
    MyCustomStateAdapter myCustomStateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        search = findViewById(R.id.edtSearchState);
        listViewState = findViewById(R.id.listViewState);
        simpleArcLoaderState = findViewById(R.id.loaderState);

        getSupportActionBar().setTitle("States and Union Territories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchDataState();

        listViewState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailActivityState.class).putExtra("pos",position));
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomStateAdapter.getFilter().filter(s);
                myCustomStateAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void fetchDataState() {
        String url = "https://api.covid19india.org/data.json";
        simpleArcLoaderState.start();
        StringRequest reqState = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObjectRoot = new JSONObject(response.toString());
                            JSONArray stateArray = jsonObjectRoot.getJSONArray("statewise");

                            for(int i=1;i<stateArray.length();i++)
                            {
                                JSONObject stateobject = stateArray.getJSONObject(i);

                                String stateName = stateobject.getString("state");
                                String stateCode = stateobject.getString("statecode");
                                String totalCases = stateobject.getString("confirmed");
                                String activeCases = stateobject.getString("active");
                                String totalDeaths = stateobject.getString("deaths");
                                String recovered = stateobject.getString("recovered");

                                stateModel = new StateModel(stateName,totalCases,totalDeaths,activeCases,recovered,stateCode);
                                stateModelList.add(stateModel);
                            }

                            myCustomStateAdapter = new MyCustomStateAdapter(StateActivity.this , stateModelList);
                            listViewState.setAdapter(myCustomStateAdapter);
                            simpleArcLoaderState.stop();
                            simpleArcLoaderState.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoaderState.stop();
                            simpleArcLoaderState.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoaderState.stop();
                simpleArcLoaderState.setVisibility(View.GONE);
                Toast.makeText(StateActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueueState = Volley.newRequestQueue(this);
        requestQueueState.add(reqState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
