package com.midterm.nguyenngocphu.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.midterm.nguyenngocphu.R;
import com.midterm.nguyenngocphu.model.Description;
import com.midterm.nguyenngocphu.viewmodel.DesApiService;
import com.midterm.nguyenngocphu.viewmodel.DesAdapter;
import com.midterm.nguyenngocphu.R;
import com.midterm.nguyenngocphu.model.Description;
import com.midterm.nguyenngocphu.viewmodel.DesAdapter;
import com.midterm.nguyenngocphu.viewmodel.DesApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {
    private DesApiService apiService;
    private RecyclerView rvDes;
    private ArrayList<Description> descriptions;
    private DesAdapter desAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
       if(getArguments()!=null)
        {

        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDes = view.findViewById(R.id.rv_des);
        descriptions = new ArrayList<Description>();
        desAdapter = new DesAdapter(descriptions);
        rvDes.setAdapter(desAdapter);
        rvDes.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = new DesApiService();
        apiService.getDescriptions()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Description>>()
                {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Description> descriptions1)
                    {   for(Description des: descriptions1){
                        descriptions.add(des);
                        desAdapter.notifyDataSetChanged();
                    }

                    }
                    @Override
                    public void onError(@NonNull Throwable e)
                    {
                        Log.d("DEBUG","Fail"+e.getMessage());
                    }
                });

    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_search, menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//            @Override
//            public boolean onQueryTextSubmit(String s)
//            {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s)
//            {
//                dogsAdapter.getFilter().filter(s);
//                return false;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}
