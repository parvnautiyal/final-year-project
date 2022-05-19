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

public class MyCustomStateAdapter extends ArrayAdapter<StateModel> {

    private Context context;
    private List<StateModel> stateModelList;
    private List<StateModel> stateModelListFiltered;


    public MyCustomStateAdapter(Context context, List<StateModel> stateModelList) {
        super(context, R.layout.list_custom_item_state, stateModelList);

        this.context = context;
        this.stateModelList = stateModelList;
        this.stateModelListFiltered = stateModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
           View view;
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item_state, null, true);
            TextView textViewState = view.findViewById(R.id.stateName);
            TextView textViewStateCode = view.findViewById(R.id.stateCode);

            textViewState.setText(stateModelListFiltered.get(position).getState());
            textViewStateCode.setText(stateModelListFiltered.get(position).getStatecode());
        Log.d("State Adapter","Value of position"+position);
        return view;
    }

    @Override
    public int getCount() {
        return stateModelListFiltered.size();
    }

    @Nullable
    @Override
    public StateModel getItem(int position) {
        return stateModelListFiltered.get(position);
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
                    filterResults.count = stateModelList.size();
                    filterResults.values = stateModelList;

                }else{
                    List<StateModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(StateModel itemsModel:stateModelList){
                        if(itemsModel.getState().toLowerCase().contains(searchStr)){
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

                stateModelListFiltered = (List<StateModel>) results.values;
                StateActivity.stateModelList = (List<StateModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

}