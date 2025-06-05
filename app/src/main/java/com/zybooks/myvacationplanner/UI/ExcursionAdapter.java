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

    class ExcursionViewHolder extends RecyclerView.ViewHolder {
        private final TextView excursionItemView;
        private ExcursionViewHolder (View itemView) {
            super(itemView);
            excursionItemView = itemView.findViewById(R.id.textViewExcursionListItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Excursion current = mExcursions.get(position);
                    Intent intent = new Intent(context, ExcursionDetails.class);
                    intent.putExtra("id", current.getExcursionID());
                    intent.putExtra("title", current.getExcursionTitle());
                    intent.putExtra("date", current.getExcursionDate());
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
            holder.excursionItemView.setText(name);

        }
        else{
            holder.excursionItemView.setText("No vacation name");
        }
    }

    public void setExcursions(List <Excursion> excursions){
        mExcursions = excursions;
        notifyDataSetChanged();
    }

    public ExcursionAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context= context;
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
