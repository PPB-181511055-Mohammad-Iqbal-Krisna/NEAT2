package com.example.neat2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.neat2.MainActivity.*;

public class MainFragment extends Fragment {
    public ArrayList<Task> tasks;
    public MyAdapter adapter;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ArrayList<Task> createTaskList() {
        context = getActivity();
        DatabaseAdapter db = new DatabaseAdapter(context);
        String Nama;
        String Details;
        String Date;

        db.open();
        Cursor c = db.getAllData();
        if(c.moveToFirst()){
            do{
                Nama=c.getString(1);
                Details=c.getString(2);
                Date=c.getString(3);
                taskArrayList.add(new Task(Nama,Details,Date));
            }while (c.moveToNext());
        }
        return taskArrayList;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView rvTask = view.findViewById(R.id.rvTask);

        tasks = createTaskList();

        adapter = new MyAdapter(tasks);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this.getActivity().getBaseContext());
        rvTask.setAdapter(adapter);
        rvTask.setLayoutManager(layoutmanager);
        rvTask.setHasFixedSize(true);

        // Inflate the layout for this fragment
        return view;

    }
}