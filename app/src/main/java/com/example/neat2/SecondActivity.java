package com.example.neat2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText taskName;
    EditText taskDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = (EditText)findViewById(R.id.tasjNameForm);
        taskDate = (EditText)findViewById(R.id.taskDateForm);

        Button button1 = findViewById(R.id.insertButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                String Name = taskName.getText().toString();
                String Date = taskDate.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("taskName",Name);
                bundle.putString("taskDate",Date);

                i.putExtras(bundle);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
