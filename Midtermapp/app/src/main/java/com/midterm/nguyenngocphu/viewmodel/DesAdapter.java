package com.midterm.nguyenngocphu.viewmodel;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.nguyenngocphu.R;
import com.midterm.nguyenngocphu.databinding.DesItemBinding;
import com.midterm.nguyenngocphu.databinding.DesItemBindingImpl;
import com.midterm.nguyenngocphu.model.Description;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


    public class DesAdapter extends RecyclerView.Adapter<DesAdapter.ViewHolder> {
        private ArrayList<Description> descriptions;
//        private  ArrayList<Description> descriptionscopy;


        public DesAdapter(ArrayList<Description> descriptions) {
            this.descriptions = descriptions;
//            this.descriptionscopy = descriptions;
        }


        @NonNull
        @Override
        public DesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            DesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.des_item, parent, false);
            return new ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.binding.setDes(descriptions.get(position));
//                holder.tvTitle.setText(descriptions.get(position).getTitle());
//            holder.tvDesc.setText(descriptions.get(position).getDesc());
//            holder.timestamp.setText(descriptions.get(position).getTimeStamp());

        }

        @Override
        public int getItemCount() {
            return descriptions.size();
        }


//        @Override
//        public Filter getFilter() {
//            return new Filter() {
//                @Override
//                protected FilterResults performFiltering(CharSequence charSequence) {
//                    String input = charSequence.toString().toLowerCase();
//                    List<DogBreed> filteredDog = new ArrayList<DogBreed>();
//                    if (input.isEmpty()) {
//                        filteredDog.addAll(dogBreedsCopy);
//                    } else {
//                        for (DogBreed dog : dogBreedsCopy) {
//                            if (dog.getName().toLowerCase().contains(input)) {
//                                filteredDog.add(dog);
//                            }
//                        }
//                    }
//                    FilterResults filterResults = new FilterResults();
//                    filterResults.values = filteredDog;
//                    return filterResults;
//                }
//
//                @Override
//                protected void publishResults(CharSequence constraint, FilterResults filterResults) {
//                    dogBreeds = new ArrayList<>();
//                    dogBreeds.addAll((List)filterResults.values);
//                    notifyDataSetChanged();
//                }
//            };
//        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public DesItemBinding binding;

            public ViewHolder(DesItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.binding = itemBinding;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Description dog = descriptions.get(getAdapterPosition());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("description", descriptions);
                        Navigation.findNavController(view).navigate(R.id.details, bundle);


                    }
                });
            }
        }
    }



