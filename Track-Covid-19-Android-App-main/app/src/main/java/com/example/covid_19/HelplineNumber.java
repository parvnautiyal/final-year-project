package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HelplineNumber extends AppCompatActivity {

    ListView listViewhelpline;
    public static List<HelplineModel> helplineModelList = new ArrayList<>();
    HelplineModel helplineModel;
    MyCustomHelplineAdapter myCustomHelplineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline_number);

        getSupportActionBar().setTitle("Helpline Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listViewhelpline = findViewById(R.id.listViewhelpline);

        String[] stateNameArray = {"Andhra Pradesh","Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
                "Gujarat", "Haryana", "HimachalPradesh", "Jharkhand", "Karnataka", "Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal","Andaman and Nicobar Islands","Chandigarh","Dadra and Nagar Haveli and Daman & Diu","Delhi","Jammu & Kashmir","Ladakh","Lakshadweep","Puducherry"};

        String[] phoneNumberArray = {"0866-2412978","0866-2412978", "943605743", "6913347770", "104", "104", "104"
                , "104", "8558893911", "104", "104", "104", "0471-2552056"
                ,"104","020-26127394","3852411668","108","102","7005539653","9439994859","104","0141-2225624","104","044-29510500","104","0381-2315879","104","18001805145","1800313444222","03192-232102","9779558282","104","011-22307145","01912520982","01982256462","104","104"};
        String[] stateCodeArray = {"AP","AP", "AR", "AS", "BR", "CT", "GA"
                , "GJ", "HR", "HP", "JH", "KA", "KL"
                ,"MP","MH","MN","ML","MZ","NL","OR","PB","RJ","SK","TN","TG","TR","UK","UP","WB","AN","CH","DN","DL","JK","LA","LD","PY"};
        for (int i=0;i<stateNameArray.length;i++)
        {
            helplineModel = new HelplineModel(stateCodeArray[i],stateNameArray[i],phoneNumberArray[i]);
            helplineModelList.add(helplineModel);
        }
        myCustomHelplineAdapter = new MyCustomHelplineAdapter(HelplineNumber.this,helplineModelList);
        listViewhelpline.setAdapter(myCustomHelplineAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
