package com.example.giuaky2022.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giuaky2022.R;
import com.example.giuaky2022.database.AppDatabase;
import com.example.giuaky2022.database.RecordDao;
import com.example.giuaky2022.model.Record;
import com.example.giuaky2022.DetailActivity;
import com.example.giuaky2022.MainActivity;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Record> recordArrayList;

    public RecordAdapter(Context context, ArrayList<Record> recordArrayList) {
        this.context = context;
        this.recordArrayList = recordArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(recordArrayList.get(position).getTitle());
        holder.tvDesc.setText(recordArrayList.get(position).getDesc());
        holder.tvTimestamp.setText(recordArrayList.get(position).getTimestamp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);
                int index = holder.getAdapterPosition();
                String title = recordArrayList.get(index).getTitle();
                String desc = recordArrayList.get(index).getDesc();
                String timestamp = recordArrayList.get(index).getTimestamp();
                String lat = recordArrayList.get(index).getLat();
                String lng = recordArrayList.get(index).getLng();
                String addr = recordArrayList.get(index).getAddr();
                String e = recordArrayList.get(index).getE();
                String zip = recordArrayList.get(index).getZip();

                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                intent.putExtra("timestamp", timestamp);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);
                intent.putExtra("addr", addr);
                intent.putExtra("e", e);
                intent.putExtra("zip", zip);

                int id = recordArrayList.get(index).getId();
                System.err.println("check id " + id);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int index = holder.getAdapterPosition();
                int id = recordArrayList.get(index).getId();
                System.err.println("check id " + id);
                AppDatabase appDatabase = AppDatabase.getInstance(context);
                RecordDao recordDao = appDatabase.recordDao();
                Record record = recordDao.findRecordById(id);
                recordDao.delete(record);
                System.err.println("delete success");
                ((MainActivity)context).onResume();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return recordArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle, tvDesc, tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_detail);
            tvDesc = itemView.findViewById(R.id.tv_desc_detail);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp_detail);
        }
    }
}
