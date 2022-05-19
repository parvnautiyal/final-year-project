package com.example.covid_19;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDistrictCustomAdapter extends ArrayAdapter<DistrictModel> {

    private Context context;
    private List<DistrictModel> districtModelList;
    private List<DistrictModel> districtModelListFiltered;

    public MyDistrictCustomAdapter(Context context, List<DistrictModel> districtModelList) {
        super(context, R.layout.list_custom_item_district,districtModelList);

        this.context = context;
        this.districtModelList = districtModelList;
        this.districtModelListFiltered = districtModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item_district, null, true);
        TextView textViewDistrict = view.findViewById(R.id.districtName);
        textViewDistrict.setText(districtModelListFiltered.get(position).getDistrictName());
        Log.d("District Adapter","Value of position "+districtModelListFiltered.get(position).getDistrictName());
        Log.d("District Adapter","Value of position "+position);
        return view;
    }
    @Override
    public int getCount() {
        return districtModelListFiltered.size();
    }

    @Nullable
    @Override
    public DistrictModel getItem(int position) {
        return districtModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = districtModelList.size();
                    filterResults.values = districtModelList;

                }else{
                    List<DistrictModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(DistrictModel itemsModel:districtModelList){
                        if(itemsModel.getDistrictName().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                districtModelListFiltered = (List<DistrictModel>) results.values;
                DistrictActivity.districtModelListForSearch = (List<DistrictModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
