package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyCustomHelplineAdapter extends ArrayAdapter<HelplineModel> {
    private Context context;
    private List<HelplineModel> helplineModelList;


    public MyCustomHelplineAdapter(@NonNull Context context, List<HelplineModel> helplineModelList) {
        super(context, R.layout.list_custom_helpline,helplineModelList);

        this.context = context;
        this.helplineModelList = helplineModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (position == 0)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, null, true);
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_helpline, null, true);
            TextView name = view.findViewById(R.id.stateNameHelpline);
            TextView phone = view.findViewById(R.id.phoneNumber);
            TextView code  = view.findViewById(R.id.code_view);

            name.setText(helplineModelList.get(position).getStateNameHelpline());
            phone.setText(helplineModelList.get(position).getPhone());
            code.setText(helplineModelList.get(position).getStateCodeHelpline());
        }
        return view;
    }
}
