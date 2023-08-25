package com.kyleknight.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String taskTitle = getIntent().getStringExtra("TASK_TITLE");
        String taskDescription = getIntent().getStringExtra("TASK_DESCRIPTION");
        String taskStatus = getIntent().getStringExtra("TASK_STATUS");

        TextView titleTextView = findViewById(R.id.textView2);
        TextView descriptionTextView = findViewById(R.id.textView3);
        TextView statusTextView = findViewById(R.id.statusTextView);

        titleTextView.setText(taskTitle);
        descriptionTextView.setText(taskDescription);
    }
}
