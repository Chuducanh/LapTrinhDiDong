package com.example.giuaky2021de1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    Context context;
    private ArrayList<Detail> detailArrayList;
    private AppDatabase appDatabase;
    private DetailDao detailDao;

    public DetailsAdapter(Context context, ArrayList<Detail> detailArrayList) {
        this.context = context;
        this.detailArrayList = detailArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvInput.setText(detailArrayList.get(position).getInput());
        holder.tvAction.setText(detailArrayList.get(position).getAction());
        holder.tvOutput.setText(detailArrayList.get(position).getOutput());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase = AppDatabase.getInstance(v.getContext());
                detailDao = appDatabase.detailDao();
                int index = holder.getAdapterPosition();
                int id = detailArrayList.get(index).getId();
                Detail detail = detailDao.findContactById(id);
                detailDao.delete(detail);
                Toast.makeText(v.getContext(), "Delete record successful", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvInput, tvAction, tvOutput;
        private Button deleteBtn;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tvInput = view.findViewById(R.id.tv_input);
            tvAction = view.findViewById(R.id.tv_action);
            tvOutput = view.findViewById(R.id.tv_output);
            deleteBtn = view.findViewById(R.id.delete_item_button);
        }
    }
}
