package com.example.neat2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String NameFromIntent;
    String DateFromIntent;
    DatabaseAdapter db = new DatabaseAdapter(this);

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    MainFragment MainFragment;

    Intent data = new Intent("com.example.tasklist.SecondActivity");

    static ArrayList<Task> taskArrayList = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment = new MainFragment();

        fragmentTransaction.replace(
                android.R.id.content, MainFragment);

        fragmentTransaction.commit();
    }

    public void addTask(View view) {
        startActivityForResult(data,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                NameFromIntent = data.getStringExtra("taskName");
                DateFromIntent = data.getStringExtra("taskDate");
                Toast.makeText(this, NameFromIntent, Toast.LENGTH_LONG).show();
                Toast.makeText(this, DateFromIntent, Toast.LENGTH_LONG).show();
                db.open();
                db.insertData(NameFromIntent,DateFromIntent);
                db.close();
                taskArrayList.add(new Task(NameFromIntent,DateFromIntent));
            }
        }
        MainFragment.adapter.notifyDataSetChanged();
        super.onActivityResult(requestCode,resultCode,data);
    }
}
