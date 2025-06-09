package com.zybooks.myvacationplanner.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.myvacationplanner.Entities.Excursion;
import com.zybooks.myvacationplanner.R;

import java.util.List;
public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionAdapter.ExcursionViewHolder> {
    private final Context context;
    private List<Excursion> mExcursions;
    private final LayoutInflater mInflater;
    private final String vacationStart;
    private final String vacationEnd;

    class ExcursionViewHolder extends RecyclerView.ViewHolder {
        private final TextView excursionItemView;
        private final TextView excursionItemView2;

        private ExcursionViewHolder (View itemView) {
            super(itemView);
            excursionItemView = itemView.findViewById(R.id.textViewExcursionListItem);
            excursionItemView2 = itemView.findViewById(R.id.textViewExcursionDateItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Excursion current = mExcursions.get(position);
                    Intent intent = new Intent(context, ExcursionDetails.class);
                    intent.putExtra("excursionid", current.getExcursionID());
                    intent.putExtra("name", current.getExcursionTitle());
                    intent.putExtra("date", current.getExcursionDate());
                    intent.putExtra("vacationStart", ((VacationDetails) context).getStartDate());
                    intent.putExtra("vacationEnd", ((VacationDetails) context).getEndDate());
                    intent.putExtra("vacationID", current.getVacationID());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public ExcursionAdapter.ExcursionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.excursion_list_item, parent, false);
        return new ExcursionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExcursionAdapter.ExcursionViewHolder holder, int position) {
        if (mExcursions != null){
            Excursion current = mExcursions.get(position);
            String name = current.getExcursionTitle();
            String date = current.getExcursionDate();
            holder.excursionItemView.setText(name);
            holder.excursionItemView2.setText(date);

        }
        else{
            holder.excursionItemView.setText("No vacation name");
        }
    }

    public void setExcursions(List <Excursion> excursions){
        mExcursions = excursions;
        notifyDataSetChanged();
    }
    public ExcursionAdapter(Context context, String vacationStart, String vacationEnd){
        mInflater= LayoutInflater.from(context);
        this.context= context;
        this.vacationStart = vacationStart;
        this.vacationEnd =vacationEnd;
    }

    @Override
    public int getItemCount() {
        if(mExcursions != null) {
            return mExcursions.size();
        }
        else {
            return 0;
        }
    }
}

